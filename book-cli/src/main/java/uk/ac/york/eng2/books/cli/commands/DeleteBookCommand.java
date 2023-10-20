package uk.ac.york.eng2.books.cli.commands;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import jakarta.inject.Inject;
import picocli.CommandLine;
import uk.ac.york.eng2.books.cli.BooksClient;

@CommandLine.Command(name = "delete-book", description = "Update book",
        mixinStandardHelpOptions = true)
public class DeleteBookCommand implements Runnable {

    @Inject
    BooksClient client;
    @CommandLine.Parameters(index = "0")
    Long bookId;


    @Override
    public void run() {
        HttpResponse<Void> result = client.deleteBook(bookId);
        
        if (result.code() == HttpStatus.NOT_FOUND.getCode()) {
            System.out.println("Could not find a book with that id");
        } else {
            System.out.println("Deleted book with id " + bookId);
        }
    }
}
