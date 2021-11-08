package com.example.third_task.repository;

import com.example.third_task.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private UserRepository repository;

    @Test
    public void testSave() {
        User user = new User(1L, "name", "username", "+37061889334", "email@mail.com", "LT", "-Password1");
        repository.save(user);

        User userById = repository.findById(1L).get();

        assertNotNull(userById);
    }

    @Test
    public void testFindAll() {
        User user = new User();
        repository.save(user);

        Iterable<User> users = repository.findAll();

        assertNotNull(users);

        List<User> result = new ArrayList<>();
        users.forEach(result::add);

        assertEquals(1, result.size());
    }

    @Test
    public void testDelete() {
        User o = new User(1L, "name", "username", "+37061889334", "email@mail.com", "LT", "-Password1");
        repository.save(o);

        User userById = repository.findById(1L).get();
        assertNotNull(userById);

        repository.delete(userById);

        Iterable<User> users = repository.findAll();

        List<User> result = StreamSupport.stream(users.spliterator(), false).collect(Collectors.toList());

        assertEquals(0, result.size());
    }

}