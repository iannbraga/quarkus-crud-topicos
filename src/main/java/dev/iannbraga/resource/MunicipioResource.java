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

@Path("/municipios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MunicipioResource {

    @Inject
    private MunicipioRepository municipioRepository;

    @Inject
    private EstadoRepository estadoRepository;

    @POST
    @Transactional
    public MunicipioResponseDTO save(MunicipioDTO dto){
        
        Municipio entity = new Municipio();

        entity.setName(dto.getName());
        entity.setEstado(estadoRepository.findById(dto.getIdEstado()));
        
        municipioRepository.persist(entity);
        
        return new MunicipioResponseDTO(entity);  
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
    public List<MunicipioResponseDTO> listDTO() {
        List<MunicipioResponseDTO> municipios = municipioRepository.findAll()
        .stream().map(
            m -> new MunicipioResponseDTO(m)
                ).collect(Collectors.toList());
        return municipios;
    }


    @GET
    @Path("/{id}")
    public MunicipioResponseDTO getById(@PathParam("id") Long id) {
        Municipio entity = municipioRepository.findById(id); 
        return new MunicipioResponseDTO(entity);
    }

    @GET
    @Path("/search/{name}")
    public List<MunicipioResponseDTO> getByName(@PathParam("name") String name) {
        List<MunicipioResponseDTO> finded = municipioRepository.findByName(name).stream()
        .map(m -> new MunicipioResponseDTO(m)).collect(Collectors.toList());
        return finded;
    }
}