package uk.ac.york.eng2.books.cli;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.http.client.annotation.Client;


@Client("${books.url:`http://localhost:8080/books`}")
public interface BooksClient {
    @Get("/")
    public Iterable<Book> list();

    @Post("/")
    public HttpResponse<Void> add(@Body BookDTO bookDetails);

    @Get("/{id}")
    public Book getBook(Long id);

    @Put("/{id}")
    public HttpResponse<Void> updateBook(long id, @Body BookDTO bookDetails);

    @Delete("/{id}")
    public HttpResponse<Void> deleteBook(long id);

    @Delete("/{id}/readers/{userId}")
    public HttpResponse<Void> deleteReader(long id, long userId);
}
