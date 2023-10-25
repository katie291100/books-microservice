package uk.ac.york.eng2.books.cli.commands;


import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import jakarta.inject.Inject;
import picocli.CommandLine;
import uk.ac.york.eng2.books.cli.BooksClient;
import uk.ac.york.eng2.books.cli.UsersClient;

@CommandLine.Command(name = "delete-reader", description = "delete reader",
        mixinStandardHelpOptions = true)
public class DeleteReaderCommand implements Runnable {

    @Inject
    BooksClient client;
    @CommandLine.Parameters(index = "0")
    Long bookId;

    @CommandLine.Parameters(index = "1")
    Long userId;


    @Override
    public void run() {
        HttpResponse<Void> result = client.deleteReader(bookId, userId);

        if (result.code() == HttpStatus.NOT_FOUND.getCode()) {
            System.out.println("Could not find a user with that id");
        } else {
            System.out.println("Deleted reader with id " + userId);
        }
    }
}