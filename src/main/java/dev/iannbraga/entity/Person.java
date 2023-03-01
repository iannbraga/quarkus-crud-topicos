package dev.iannbraga.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Person {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name", length = 60, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 60, nullable = false)
    private String lastName;

    @Column(length = 60, nullable = false)
    private String cpf;

    @Column(length = 60, nullable = false)
    private String email;

    @Column(length = 60, nullable = false)
    private String password;
}
