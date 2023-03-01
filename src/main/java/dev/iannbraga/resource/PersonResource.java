package dev.iannbraga.resource;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dev.iannbraga.entity.Person;
import dev.iannbraga.repository.PersonRepository;

@Path("/person")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {

    @Inject
    private PersonRepository personRepository;

    @GET
    public List<Person> hello() {
        return personRepository.findAll().list();
    }

    @POST
    @Transactional
    public Person save(Person newPerson){
        personRepository.persist(newPerson);
        return newPerson;  
    }
}