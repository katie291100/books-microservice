package uk.ac.york.eng2.books.cli.commands;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import jakarta.inject.Inject;
import picocli.CommandLine;
import uk.ac.york.eng2.books.cli.UsersClient;

@CommandLine.Command(name = "delete-user", description = "delete user",
        mixinStandardHelpOptions = true)
public class DeleteUserCommand implements Runnable {

    @Inject
    UsersClient client;
    @CommandLine.Parameters(index = "0")
    Long userId;


    @Override
    public void run() {
        HttpResponse<Void> result = client.deleteUser(userId);

        if (result.code() == HttpStatus.NOT_FOUND.getCode()) {
            System.out.println("Could not find a user with that id");
        } else {
            System.out.println("Deleted user with id " + userId);
        }
    }
}
