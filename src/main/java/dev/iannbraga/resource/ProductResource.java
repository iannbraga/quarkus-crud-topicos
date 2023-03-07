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

import dev.iannbraga.entity.Product;
import dev.iannbraga.repository.ProductRepository;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {

    @Inject
    private ProductRepository productRepository;

    @POST
    @Transactional
    public Product save(Product newProduct){
        productRepository.persist(newProduct);
        return newProduct;  
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Product update(@PathParam("id") Long id, Product receivedProduct){
        return validateProduct(id, receivedProduct);

    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public String deleteById(@PathParam("id") Long id){
            if(productRepository.deleteById(id)){
                return "Deleted with success";
            }else {
                return "Not found";
            }
    }

    @GET
    public List<Product> listAll() {
        return productRepository.findAllProducts();
    }

    @GET
    @Path("/{id}")
    public Product getById(@PathParam("id") Long id) {
        return productRepository.findById(id);
    }

    @GET
    @Path("/search/{name}")
    public List<Product> getByName(@PathParam("name") String name) {
        return productRepository.findByName(name);
    }

    private Product validateProduct(Long id, Product receivedProduct){
        Product persistedProduct = productRepository.findById(id);
        
        if(receivedProduct.getName() != null && !receivedProduct.getName().isBlank()){
            persistedProduct.setName(receivedProduct.getName());
        }
        if(receivedProduct.getDescription() != null && !receivedProduct.getDescription().isBlank()){
            persistedProduct.setDescription(receivedProduct.getDescription());
        }            
        if(receivedProduct.getCategory() != null && !receivedProduct.getCategory().isBlank()){
            persistedProduct.setCategory(receivedProduct.getCategory());
        }
        if(receivedProduct.getPrice() != null && receivedProduct.getPrice() > 0){
            persistedProduct.setPrice(receivedProduct.getPrice());
        }
        if(receivedProduct.getStock() > 0){
            persistedProduct.setStock(receivedProduct.getStock());
        }

        return persistedProduct;
    }
}