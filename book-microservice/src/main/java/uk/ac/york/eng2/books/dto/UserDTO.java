package uk.ac.york.eng2.books.dto;

import io.micronaut.serde.annotation.Serdeable;
import uk.ac.york.eng2.books.domain.Book;

import java.util.Set;

@Serdeable
public class UserDTO {

    private String name;

    private Set<Book> readBooks;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Set<Book> getReadBooks() {
        return readBooks;
    }

    public void setReadBooks(Set<Book> readBooks) {
        this.readBooks = readBooks;
    }
}