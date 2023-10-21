package uk.ac.york.eng2.books.cli.commands;

import jakarta.inject.Inject;
import picocli.CommandLine;
import uk.ac.york.eng2.books.cli.User;
import uk.ac.york.eng2.books.cli.UsersClient;

@CommandLine.Command(name = "get-users", description = "get all users",
        mixinStandardHelpOptions = true)
public class GetUsersCommand implements Runnable {
    @Inject
    UsersClient client;

    @Override
    public void run() {
        Iterable<User> result = client.list();
        result.forEach(it -> System.out.println(it.toString()));

    }
}
