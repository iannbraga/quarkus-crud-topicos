package dev.iannbraga.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import dev.iannbraga.entity.Product;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class ProductRepository implements PanacheRepository<Product>{

    public List<Product> findByName(String name) {
        String like = "%"+name+"%";
        
        return find("name like ?1", like).list();
    }

    public List<Product> findAllProducts() {
        return find("order by id").list();
    }
    
}
