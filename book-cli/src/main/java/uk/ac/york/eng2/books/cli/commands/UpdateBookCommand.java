package uk.ac.york.eng2.books.cli.commands;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import jakarta.inject.Inject;
import picocli.CommandLine;
import uk.ac.york.eng2.books.cli.Book;
import uk.ac.york.eng2.books.cli.BookDTO;
import uk.ac.york.eng2.books.cli.BooksClient;

@CommandLine.Command(name = "update-book", description = "Update book",
        mixinStandardHelpOptions = true)
public class UpdateBookCommand implements Runnable {

    @Inject
    BooksClient client;
    @CommandLine.Parameters(index = "0")
    Long bookId;

    @CommandLine.Option(names = {"-t", "--title"}, description = "Title of the book")
    private String title;

    @CommandLine.Option(names = {"-y", "--year"}, description = "Year of the book")
    private Integer year;

    @Override
    public void run() {
        BookDTO bookInfo = new BookDTO();
        bookInfo.setTitle(title);
        bookInfo.setYear(year);
        HttpResponse<Void> result = client.updateBook(bookId, bookInfo);
        if (result.code() == HttpStatus.NOT_FOUND.getCode()) {
            System.out.println("Could not find a book with that id");
        } else {
            System.out.println(
                    "Updated book with id " + bookId + " with the following details: " + bookInfo);
        }
    }

}
