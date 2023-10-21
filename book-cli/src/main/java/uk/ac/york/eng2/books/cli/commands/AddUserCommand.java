package uk.ac.york.eng2.books.cli.commands;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import uk.ac.york.eng2.books.cli.UsersClient;
import uk.ac.york.eng2.books.cli.UserDTO;

@Command(name = "add-user", description = "get all books",
        mixinStandardHelpOptions = true)
public class AddUserCommand implements Runnable {
    @Inject
    UsersClient client;

    @Parameters(index = "0")
    private String name;

    @Override
    public void run() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(name);
        client.add(userDTO);
    }

}
