package uk.ac.york.eng2.books.events;

import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;
import uk.ac.york.eng2.books.domain.Book;

@KafkaListener(groupId = "books-debug")
public class BooksConsumer {

    @Topic("read-books")
    void notifyNewReader(@KafkaKey long id, Book book) {
        System.out.printf("Book read: : %s%n", id);
    }
}
