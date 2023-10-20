package uk.ac.york.eng2.books.cli;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
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


}
