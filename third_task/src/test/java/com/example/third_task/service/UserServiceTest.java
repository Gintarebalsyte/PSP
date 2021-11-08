package com.example.third_task.service;

import com.example.third_task.model.User;
import com.example.third_task.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository repository;

    @Mock
    ValidationService validationService;

    @InjectMocks
    UserService service;

    @Test
    void testFindById() {
        User user = new User();
        when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(user));

        User found = service.findById(1L);
        verify(repository).findById(Mockito.anyLong());
        assertNotNull(found);
    }

    @Test
    void testFindAll() {
        User user = new User();
        List<User> users = new ArrayList<>();
        users.add(user);
        when(repository.findAll()).thenReturn(users);

        List<User> found = service.findAll();
        verify(repository).findAll();
        assertEquals(1, found.size());
    }

    @Test
    void testAdd() {
        User user = new User();
        when(repository.save(Mockito.any(User.class))).thenReturn(user);

        User added = service.add(user);
        verify(repository).save(Mockito.any(User.class));
        assertNotNull(added);
    }

    @Test
    void testUpdate() {
        User user = new User();
        service.update(user);
        verify(repository).save(Mockito.any(User.class));
    }

    @Test
    void testUpdateById() {
        User user = new User();
        service.updateById(1L, user);
        verify(repository).save(Mockito.any(User.class));
    }

    @Test
    void testDeleteById() {
        service.deleteById(1L);
        verify(repository).deleteById(Mockito.anyLong()); // verify that mock method was called one time
    }

    @Test
    void testDelete() {
        User user = new User();
        service.delete(user);
        verify(repository).delete(Mockito.any(User.class));
    }
}