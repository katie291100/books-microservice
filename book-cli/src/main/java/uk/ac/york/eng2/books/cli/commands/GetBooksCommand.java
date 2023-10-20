package uk.ac.york.eng2.books.cli.commands;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import uk.ac.york.eng2.books.cli.Book;
import uk.ac.york.eng2.books.cli.BooksClient;

@Command(name = "get-books", description = "get all books",
mixinStandardHelpOptions = true)
public class GetBooksCommand implements Runnable {
	@Inject
	BooksClient client;

	@Override
	public void run() {
		Iterable<Book> result = client.list();
		result.forEach(it -> System.out.println(it.toString()));
		
	} 
	
}
