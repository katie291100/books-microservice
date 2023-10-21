package uk.ac.york.eng2.books.dto;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.Column;
import uk.ac.york.eng2.books.domain.Book;
import uk.ac.york.eng2.books.domain.User;

import java.util.Set;

@Serdeable
public class BookDTO {

    private String title;

    private Integer year;

    public Set<User> getReaders() {
        return readers;
    }

    public void setReaders(Set<User> readers) {
        this.readers = readers;
    }

    private Set<User> readers;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}