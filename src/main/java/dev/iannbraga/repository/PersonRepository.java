package dev.iannbraga.repository;

import javax.enterprise.context.ApplicationScoped;

import dev.iannbraga.entity.Person;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class PersonRepository implements PanacheRepository<Person>{
    
}
