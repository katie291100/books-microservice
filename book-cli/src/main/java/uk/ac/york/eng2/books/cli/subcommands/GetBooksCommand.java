package uk.ac.york.eng2.books.cli.subcommands;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import uk.ac.york.eng2.books.cli.BooksClient;

@Command(name = "get-books", description = "get all books",
mixinStandardHelpOptions = true)
public class GetBooksCommand implements Runnable {
	@Inject
	BooksClient client;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(client.list());
		
	} 
	
}
