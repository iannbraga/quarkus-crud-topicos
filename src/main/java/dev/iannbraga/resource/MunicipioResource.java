package dev.iannbraga.resource;

import java.util.List;
import java.util.stream.Collectors;

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

import dev.iannbraga.dto.MunicipioDTO;
import dev.iannbraga.dto.MunicipioResponseDTO;
import dev.iannbraga.entity.Municipio;
import dev.iannbraga.repository.EstadoRepository;
import dev.iannbraga.repository.MunicipioRepository;

@Path("/municipio")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MunicipioResource {

    @Inject
    private MunicipioRepository municipioRepository;

    @Inject
    private EstadoRepository estadoRepository;

    @POST
    @Transactional
    public Municipio save(MunicipioDTO dto){
        
        Municipio entity = new Municipio();

        entity.setName(dto.getName());
        entity.setEstado(estadoRepository.findById(dto.getIdEstado()));
        
        municipioRepository.persist(entity);
        
        return entity;  
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public String deleteById(@PathParam("id") Long id){
            if(municipioRepository.deleteById(id)){
                return "Deleted with success";
            }else {
                return "Not found";
            }
    }

    @GET
    public List<Municipio> listAllOrderById() {
        return municipioRepository.findAllPeople();
    }

    @GET
    @Path("/dto")
    public List<MunicipioResponseDTO> listDTO() {
        List<MunicipioResponseDTO> municipios = municipioRepository.findAll()
        .stream().map(
            m -> new MunicipioResponseDTO(m)
                ).collect(Collectors.toList());
        return municipios;
    }


    @GET
    @Path("/{id}")
    public Municipio getById(@PathParam("id") Long id) {
        return municipioRepository.findById(id);
    }

    @GET
    @Path("/search/{name}")
    public List<Municipio> getByName(@PathParam("name") String name) {
        return municipioRepository.findByName(name);
    }

    // private Municipio validateMunicipio(Long id, Municipio receivedMunicipio){
    //     Municipio persistedMunicipio = MunicipioRepository.findById(id);
        
    //     if(receivedMunicipio.getFirstName() != null && !receivedMunicipio.getFirstName().isBlank()){
    //         persistedMunicipio.setFirstName(receivedMunicipio.getFirstName());
    //     }
    //     if(receivedMunicipio.getLastName() != null && !receivedMunicipio.getLastName().isBlank()){
    //         persistedMunicipio.setLastName(receivedMunicipio.getLastName());
    //     }            
    //     if(receivedMunicipio.getEmail() != null && !receivedMunicipio.getEmail().isBlank()){
    //         persistedMunicipio.setEmail(receivedMunicipio.getEmail());
    //     }
    //     if(receivedMunicipio.getCpf() != null && !receivedMunicipio.getCpf().isBlank()){
    //         persistedMunicipio.setCpf(receivedMunicipio.getCpf());
    //     }
    //     if(receivedMunicipio.getPassword() != null && !receivedMunicipio.getPassword().isBlank()){
    //         persistedMunicipio.setPassword(receivedMunicipio.getPassword());
    //     }

    //     return persistedMunicipio;
    // }
}