package uk.ac.york.eng2.books.controllers;


import java.net.URI;
import java.util.List;
import java.util.Set;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.transaction.annotation.Transactional;
import jakarta.inject.Inject;
import uk.ac.york.eng2.books.domain.Book;
import uk.ac.york.eng2.books.domain.User;
import uk.ac.york.eng2.books.dto.BookDTO;
import uk.ac.york.eng2.books.events.BooksProducer;
import uk.ac.york.eng2.books.repositories.BooksRepository;
import uk.ac.york.eng2.books.repositories.UsersRepository;

@Controller("/books")
public class BooksController {
    @Inject
    private BooksRepository repo;
    @Inject
    private UsersRepository userRepo;
    @Inject
    private BooksProducer kafkaClient;

    @Get("/")
    public Iterable<Book> list() {
        return repo.findAll();
    }

    @Get("/{id}")
    public Book getBook(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Post("/")
    public HttpResponse<Void> add(@Body BookDTO bookDetails) {
        Book book = new Book();
        book.setTitle(bookDetails.getTitle());
        book.setYear(bookDetails.getYear());
        repo.save(book);
        return HttpResponse.created(URI.create("/books/" + book.getId()));

    }

    @Transactional
    @Put("/{id}")
    public HttpResponse<Void> updateBook(long id, @Body BookDTO book) {
        Book bookRecord = repo.findById(id).orElse(null);

        if (bookRecord == null) {
            return HttpResponse.notFound();
        }
        if (book.getTitle() != null) {
            bookRecord.setTitle(book.getTitle());
        }
        if (book.getYear() != null) {
            bookRecord.setYear(book.getYear());
        }

        repo.update(bookRecord);
        return HttpResponse.ok();
    }

    @Delete("/{id}")
    @Transactional
    public HttpResponse<Void> deleteBook(long id) {
        Book bookRecord = repo.findById(id).orElse(null);

        if (bookRecord == null) {
            return HttpResponse.notFound();
        }

        repo.delete(bookRecord);
        return HttpResponse.ok();
    }

    @Get("/{id}/readers")
    public Set<User> getReaders(long id) {
        Book bookRecord = repo.findById(id).orElse(null);

        if (bookRecord == null) {
            return null;
        }


        return bookRecord.getReaders();
    }

    @Transactional
    @Put("/{bookId}/readers/{userId}")
    public HttpResponse<Void> addReader(long bookId, long userId) {
        Book bookRecord = repo.findById(bookId).orElse(null);
        User userRecord = userRepo.findById(userId).orElse(null);
        if (bookRecord == null || userRecord == null) {
            return HttpResponse.notFound();
        }
        Set<User> currentReaders = bookRecord.getReaders();
        currentReaders.add(userRecord);
        bookRecord.setReaders(currentReaders);
        if (bookRecord.getReaders().add(userRecord)) {
            kafkaClient.readBook(bookId, bookRecord);
        }

        return HttpResponse.ok();
    }

    @Transactional
    @Delete("/{bookId}/readers/{userId}")
    public HttpResponse<Void> deleteReader(long bookId, long userId) {
        Book bookRecord = repo.findById(bookId).orElse(null);
        User userRecord = userRepo.findById(userId).orElse(null);
        if (bookRecord == null || userRecord == null) {
            return HttpResponse.notFound();
        }
        Set<User> currentReaders = bookRecord.getReaders();
        currentReaders.remove(userRecord);
        bookRecord.setReaders(currentReaders);
        return HttpResponse.ok();
    }

}