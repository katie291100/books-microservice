package uk.ac.york.eng2.books.cli.subcommands;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import uk.ac.york.eng2.books.cli.BookDTO;
import uk.ac.york.eng2.books.cli.BooksClient;

@Command(name = "add-book", description = "get all books",
mixinStandardHelpOptions = true)
public class AddBookCommand implements Runnable {
	@Inject
	BooksClient client;
	
	@Parameters(index="0")
	private String title;

	@Parameters(index="1")
	private Integer year;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		BookDTO bookDTO = new BookDTO();
		bookDTO.setTitle(title);
		bookDTO.setYear(year);
		client.add(bookDTO);
	} 
	
}
