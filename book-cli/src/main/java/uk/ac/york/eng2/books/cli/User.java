package uk.ac.york.eng2.books.cli;

import io.micronaut.serde.annotation.Serdeable;


@Serdeable
public class User {


    private Long id;

    private String name;

    public Long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}