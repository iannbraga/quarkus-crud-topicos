package dev.iannbraga.resource;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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

    @GET
    @Path("/{id}")
    public Person getById(@PathParam("id") Long id) {
        return personRepository.findById(id);
    }

    @POST
    @Transactional
    public Person save(Person newPerson){
        personRepository.persist(newPerson);
        return newPerson;  
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Person update(@PathParam("id") Long id, Person receivedPerson){
        // Person persistedPerson = personRepository.findById(id);
        // persistedPerson.setFirstName(receivedPerson.getFirstName());
        // persistedPerson.setLastName(receivedPerson.getLastName());
        // persistedPerson.setEmail(receivedPerson.getEmail());
        // persistedPerson.setCpf(receivedPerson.getCpf());
        // persistedPerson.setPassword(receivedPerson.getPassword());

        return validatePerson(id, receivedPerson);

    }

    private Person validatePerson(Long id, Person receivedPerson){
        Person persistedPerson = personRepository.findById(id);
        
        if(receivedPerson.getFirstName() != null && !receivedPerson.getFirstName().isBlank()){
            persistedPerson.setFirstName(receivedPerson.getFirstName());
        }
        if(receivedPerson.getLastName() != null && !receivedPerson.getLastName().isBlank()){
            persistedPerson.setLastName(receivedPerson.getLastName());
        }            
        if(receivedPerson.getEmail() != null && !receivedPerson.getEmail().isBlank()){
            persistedPerson.setEmail(receivedPerson.getEmail());
        }
        if(receivedPerson.getCpf() != null && !receivedPerson.getCpf().isBlank()){
            persistedPerson.setCpf(receivedPerson.getCpf());
        }
        if(receivedPerson.getPassword() != null && !receivedPerson.getPassword().isBlank()){
            persistedPerson.setPassword(receivedPerson.getPassword());
        }

        return persistedPerson;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public String deleteById(@PathParam("id") Long id){
            if(personRepository.deleteById(id)){
                return "Deleted with success";
            }else {
                return "Not found";
            }
    }
}