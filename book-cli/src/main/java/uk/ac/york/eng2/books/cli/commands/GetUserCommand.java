package uk.ac.york.eng2.books.cli.commands;

import jakarta.inject.Inject;
import picocli.CommandLine;
import uk.ac.york.eng2.books.cli.User;
import uk.ac.york.eng2.books.cli.UsersClient;

@CommandLine.Command(name = "get-user", description = "get all users",
        mixinStandardHelpOptions = true)
public class GetUserCommand implements Runnable {
    @Inject
    UsersClient client;

    @CommandLine.Parameters(index = "0")
    public Long id;

    @Override
    public void run() {
        User result = client.getUser(id);
        if (result == null) {
            System.out.println("Couldn't find user with id " + id);
            return;
        }
        System.out.println(result);
    }
}
