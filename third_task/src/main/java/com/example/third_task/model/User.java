package com.example.third_task.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private String address;
    private String password;
//
//    public User(String name, String surname, String phoneNumber, String email, String address, String password) {
//        this.name = name;
//        this.surname = surname;
//        this.phoneNumber = phoneNumber;
//        this.email = email;
//        this.address = address;
//        this.password = password;
//    }
}
