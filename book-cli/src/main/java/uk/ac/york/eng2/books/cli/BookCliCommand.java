package uk.ac.york.eng2.books.cli;

import io.micronaut.configuration.picocli.PicocliRunner;
import io.micronaut.context.ApplicationContext;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import uk.ac.york.eng2.books.cli.subcommands.AddBookCommand;
import uk.ac.york.eng2.books.cli.subcommands.GetBooksCommand;

@Command(name = "book-cli", description = "...",
        mixinStandardHelpOptions = true, subcommands = {GetBooksCommand.class, AddBookCommand.class})
public class BookCliCommand implements Runnable {

    @Option(names = {"-v", "--verbose"}, description = "...")
    boolean verbose;

    public static void main(String[] args) throws Exception {
        PicocliRunner.run(BookCliCommand.class, args);
    }

    public void run() {
        // business logic here
        if (verbose) {
            System.out.println("Hi!");
        }
    }
}
