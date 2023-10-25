package uk.ac.york.eng2.books.events;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;
import uk.ac.york.eng2.books.domain.Book;

@KafkaClient
public interface BooksProducer {

    @Topic("read-books")
    void readBook(@KafkaKey Long key, Book b);
}


