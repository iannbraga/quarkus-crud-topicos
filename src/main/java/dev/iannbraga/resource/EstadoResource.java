package dev.iannbraga.resource;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dev.iannbraga.entity.Estado;
import dev.iannbraga.repository.EstadoRepository;

@Path("/estado")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EstadoResource {

    @Inject
    private EstadoRepository estadoRepository;

    @POST
    @Transactional
    public Estado save(Estado newEstado){
        estadoRepository.persist(newEstado);
        return newEstado;  
    }

    // @PUT
    // @Path("/{id}")
    // @Transactional
    // public Estado update(@PathParam("id") Long id, Estado receivedEstado){
    //     return validateEstado(id, receivedEstado);

    // }

    @DELETE
    @Path("/{id}")
    @Transactional
    public String deleteById(@PathParam("id") Long id){
            if(estadoRepository.deleteById(id)){
                return "Deleted with success";
            }else {
                return "Not found";
            }
    }

    @GET
    public List<Estado> listAll() {
        // return estadoRepository.findAll().list();
        return estadoRepository.findAllPeople();
    }

    @GET
    @Path("/{id}")
    public Estado getById(@PathParam("id") Long id) {
        return estadoRepository.findById(id);
    }

    @GET
    @Path("/search/{name}")
    public List<Estado> getByName(@PathParam("name") String name) {
        return estadoRepository.findByName(name);
    }

    // private Estado validateEstado(Long id, Estado receivedEstado){
    //     Estado persistedEstado = estadoRepository.findById(id);
        
    //     if(receivedEstado.getFirstName() != null && !receivedEstado.getFirstName().isBlank()){
    //         persistedEstado.setFirstName(receivedEstado.getFirstName());
    //     }
    //     if(receivedEstado.getLastName() != null && !receivedEstado.getLastName().isBlank()){
    //         persistedEstado.setLastName(receivedEstado.getLastName());
    //     }            
    //     if(receivedEstado.getEmail() != null && !receivedEstado.getEmail().isBlank()){
    //         persistedEstado.setEmail(receivedEstado.getEmail());
    //     }
    //     if(receivedEstado.getCpf() != null && !receivedEstado.getCpf().isBlank()){
    //         persistedEstado.setCpf(receivedEstado.getCpf());
    //     }
    //     if(receivedEstado.getPassword() != null && !receivedEstado.getPassword().isBlank()){
    //         persistedEstado.setPassword(receivedEstado.getPassword());
    //     }

    //     return persistedEstado;
    // }
}