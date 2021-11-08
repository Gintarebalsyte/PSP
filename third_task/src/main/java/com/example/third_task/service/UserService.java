package com.example.third_task.service;

import com.company.Validator;
import com.example.third_task.model.User;
import com.example.third_task.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository repository;

    @Autowired
    ValidationService validationService;

    public User findById(Long id){
        return repository.findById(id).get();
    }

    public List<User> findAll(){
        return (List<User>) repository.findAll();
    }

    public User add(User user) throws IllegalArgumentException{
        validationService.validate(user);
        return repository.save(user);
    }

    public void update(User user){
        validationService.validate(user);
        repository.save(user);
    }

    public void updateById(Long id, User user){
        user.setId(id);
        repository.save(user);
    }

    public void delete(User user){
        repository.delete(user);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }
}
