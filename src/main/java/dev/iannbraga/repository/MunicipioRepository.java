package dev.iannbraga.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import dev.iannbraga.entity.Municipio;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class MunicipioRepository implements PanacheRepository<Municipio>{

    public List<Municipio> findByName(String name) {
        String like = "%"+name+"%";
        
        return find("name like ?1", like).list();
    }

    public List<Municipio> findAllPeople() {
        return find("order by id").list();
    }
}
