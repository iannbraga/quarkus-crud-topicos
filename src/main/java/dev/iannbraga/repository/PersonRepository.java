package dev.iannbraga.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import dev.iannbraga.entity.Person;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class PersonRepository implements PanacheRepository<Person>{

    public List<Person> findByName(String name) {
        String like = "%"+name+"%";
        
        return find("first_name like ?1", like).list();
    }
    
}
