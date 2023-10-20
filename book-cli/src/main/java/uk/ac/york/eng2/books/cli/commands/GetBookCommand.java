package uk.ac.york.eng2.books.cli.commands;

import jakarta.inject.Inject;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import uk.ac.york.eng2.books.cli.Book;
import uk.ac.york.eng2.books.cli.BooksClient;

@Command(name = "get-book", description = "get all books",
        mixinStandardHelpOptions = true)
public class GetBookCommand implements Runnable {
    @Inject
    BooksClient client;

    @CommandLine.Parameters(index = "0")
    private Long id;
    @Override
    public void run() {
        Book result = client.getBook(id);
        if (result == null) {
            System.out.println("Couldn't find book with id " + id);
            return;
        }
        System.out.println(result);
    }

}
