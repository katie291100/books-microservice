package uk.ac.york.eng2.books.cli;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.http.client.annotation.Client;


@Client("${users.url:`http://localhost:8080/users`}")
public interface UsersClient {
    @Get()
    Iterable<User> list();

    @Post()
    HttpResponse<Void> add(@Body UserDTO userDetails);

    @Get("/{id}")
    User getUser(Long id);

    @Put("/{id}")
    HttpResponse<Void> updateUser(long id, @Body UserDTO userDetails);

    @Delete("/{id}")
    HttpResponse<Void> deleteUser(long id);
}
