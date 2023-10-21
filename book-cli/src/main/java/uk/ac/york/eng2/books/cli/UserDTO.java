package uk.ac.york.eng2.books.cli;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class UserDTO {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "UserDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}