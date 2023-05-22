package com.bluethink.dev.controller;

import com.bluethink.dev.enitity.User;
import com.bluethink.dev.service.UserService;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;

import java.util.List;
import java.util.Optional;


@ExecuteOn(TaskExecutors.IO)
@Controller("/users")
public class UserController {

    protected final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @Get
    public List<User> getUsers() {
        return userService.findAll();
    }

    @Get("/{id}")
    public Optional<User> getUser(Long id) {
        return userService.findById(id);
    }

    @Put("/{id}")
    public void updateUser(Long id, @Body User user) {
        userService.update(id, user);
    }

    @Post
    public User addUser(@Body User user) {
        return userService.save(user);
    }

    @Delete("/{id}")
    public void deleteUser(Long id) {
        userService.deleteById(id);
    }
}