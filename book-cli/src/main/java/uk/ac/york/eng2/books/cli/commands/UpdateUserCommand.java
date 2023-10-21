package uk.ac.york.eng2.books.cli.commands;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import jakarta.inject.Inject;
import picocli.CommandLine;
import uk.ac.york.eng2.books.cli.UserDTO;
import uk.ac.york.eng2.books.cli.UsersClient;

@CommandLine.Command(name = "update-user", description = "Update book",
        mixinStandardHelpOptions = true)
public class UpdateUserCommand implements Runnable {

    @Inject
    UsersClient client;
    @CommandLine.Parameters(index = "0")
    Long userId;

    @CommandLine.Option(names = {"-n", "--name"}, description = "Name of the User")
    public String name;

    @Override
    public void run() {
        UserDTO userInfo = new UserDTO();
        userInfo.setName(name);
        HttpResponse<Void> result = client.updateUser(userId, userInfo);
        if (result.code() == HttpStatus.NOT_FOUND.getCode()) {
            System.out.println("Could not find a user with that id");
        } else {
            System.out.println(
                    "Updated user with id " + userId + " with the following details: " + userInfo);
        }
    }

}
