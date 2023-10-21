package uk.ac.york.eng2.users.controllers;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.transaction.annotation.Transactional;
import jakarta.inject.Inject;
import uk.ac.york.eng2.books.domain.Book;
import uk.ac.york.eng2.books.domain.User;
import uk.ac.york.eng2.books.dto.BookDTO;
import uk.ac.york.eng2.books.dto.UserDTO;
import uk.ac.york.eng2.books.repositories.UsersRepository;

import java.net.URI;

@Controller("/users")
public class UsersController {
    @Inject
    private UsersRepository repo;

    @Get("/")
    public Iterable<User> list() {
        return repo.findAll();
    }

    @Get("/{id}")
    public User getUser(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Post("/")
    public HttpResponse<Void> add(@Body UserDTO userDetails) {
        User user = new User();
        user.setName(userDetails.getName());
        repo.save(user);
        return HttpResponse.created(URI.create("/users/" + user.getId()));
    }

    @Transactional
    @Put("/{id}")
    public HttpResponse<Void> updateUser(long id, @Body UserDTO userDetails) {
        User userRecord = repo.findById(id).orElse(null);

        if (userRecord == null) {
            return HttpResponse.notFound();
        }
        if (userDetails.getName() != null) {
            userRecord.setName(userDetails.getName());
        }

        repo.update(userRecord);
        return HttpResponse.ok();
    }

    @Delete("/{id}")
    @Transactional
    public HttpResponse<Void> deleteUser(long id) {
        User userRecord = repo.findById(id).orElse(null);

        if (userRecord == null) {
            return HttpResponse.notFound();
        }

        repo.delete(userRecord);
        return HttpResponse.ok();
    }
}