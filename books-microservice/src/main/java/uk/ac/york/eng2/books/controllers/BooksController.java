package uk.ac.york.eng2.books.controllers;


import java.net.URI;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;
import uk.ac.york.eng2.books.domain.Book;
import uk.ac.york.eng2.books.dto.BookDTO;
import uk.ac.york.eng2.books.repositories.BooksRepository;

@Controller("/books")
public class BooksController {
	@Inject 
	private BooksRepository repo;
	
	@Get("/")
	public Iterable<Book> list(){
		return repo.findAll();
	}
	
	@Post("/") 
	public HttpResponse<Void> add(@Body BookDTO bookDetails) {
		Book book = new Book();
		book.setTitle(bookDetails.getTitle());
		book.setYear(bookDetails.getYear());
//		book.setId( (long) 0);
		repo.save(book);
		return HttpResponse.created(URI.create("/books/" + book.getId()));
		
	} 
}