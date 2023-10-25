package uk.ac.york.eng2.books.events;

import io.micronaut.configuration.kafka.serde.CompositeSerdeRegistry;
import io.micronaut.configuration.kafka.streams.ConfiguredStreamBuilder;
import io.micronaut.context.annotation.Factory;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.time.Duration;
import java.util.Properties;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import uk.ac.york.eng2.books.domain.Book;
import uk.ac.york.eng2.books.events.WindowedIdentifier;

@Factory
public class BooksStream {
    @Inject
    private CompositeSerdeRegistry serdeRegistry;

    @Singleton
    KStream<WindowedIdentifier, Long> readByDay(ConfiguredStreamBuilder builder) {
        Properties props = builder.getConfiguration();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "books-metrics");
        props.put(StreamsConfig.PROCESSING_GUARANTEE_CONFIG, StreamsConfig.EXACTLY_ONCE_V2);

        KStream<WindowedIdentifier, Long> stream =
                builder.stream(
                                "read-books", Consumed.with(Serdes.Long(), serdeRegistry.getSerde(Book.class)))
                        .selectKey((userId, book) -> book.getId())
                        .groupByKey(Grouped.with(Serdes.Long(), serdeRegistry.getSerde(Book.class)))
                        .windowedBy(TimeWindows.ofSizeWithNoGrace(Duration.ofHours(24)).advanceBy(Duration.ofHours(24)))
                        .count()
                        .toStream()
                        .selectKey(
                                (windowedBookId, readerCount) ->
                                        new WindowedIdentifier(
                                                windowedBookId.key(),
                                                windowedBookId.window().start(),
                                                windowedBookId.window().end()));

        stream.to("book-read-by-day", Produced.with(serdeRegistry.getSerde(WindowedIdentifier.class), Serdes.Long()));
        stream.print(Printed.toSysOut());

        return stream;
    }
}