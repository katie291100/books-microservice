package uk.ac.york.eng2.books.repositories;

import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import jakarta.validation.constraints.NotNull;
import uk.ac.york.eng2.books.domain.Book;

import java.util.Optional;

@Repository
public interface BooksRepository extends CrudRepository<Book, Long> {

}