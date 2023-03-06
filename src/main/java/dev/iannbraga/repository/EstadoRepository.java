package dev.iannbraga.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import dev.iannbraga.entity.Estado;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class EstadoRepository implements PanacheRepository<Estado>{

    public List<Estado> findByName(String name) {
        String like = "%"+name+"%";
        
        return find("name like ?1", like).list();
    }

    public List<Estado> findAllPeople() {
        return find("order by id").list();
    }
    
}
