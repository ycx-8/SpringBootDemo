package com.example.SpringBootDemo;

import com.example.SpringBootDemo.Exceptions.ProductNotFoundException;
import com.example.SpringBootDemo.Product.Model.Product;
import com.example.SpringBootDemo.Product.Model.ProductDTO;
import com.example.SpringBootDemo.Product.commandhandlers.CreateProductCommandHandler;
import com.example.SpringBootDemo.Product.commandhandlers.DeleteProductCommandHandler;
import com.example.SpringBootDemo.Product.commandhandlers.UpdateProductCommandHandler;
import com.example.SpringBootDemo.Product.queryhandlers.GetAllProductsQueryHandler;
import com.example.SpringBootDemo.Product.queryhandlers.GetProductQueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    // Dependency injection: field injection.
    // Annotations like Repository and Service create beans.
    // Constructor injections use more code than field injection.
    // Advantage of using constructor injection: easier to test?
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private GetAllProductsQueryHandler getAllProductsQueryHandler;
    @Autowired
    private GetProductQueryHandler getProductQueryHandler;
    @Autowired
    private CreateProductCommandHandler createProductCommandHandler;
    @Autowired
    private UpdateProductCommandHandler updateProductCommandHandler;
    @Autowired
    private DeleteProductCommandHandler deleteProductCommandHandler;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getProducts() {
        return getAllProductsQueryHandler.execute(null);
    }

    // {id} in @GetMapping must match id in method parameter
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Integer id) {
        return getProductQueryHandler.execute(id);
    }

    @PostMapping
    public ResponseEntity createProduct(@RequestBody Product product) {
        return createProductCommandHandler.execute(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        UpdateProductCommand command = new UpdateProductCommand(id, product);
        return updateProductCommandHandler.execute(command);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable Integer id) {
        return deleteProductCommandHandler.execute(id);
    }

    /* If you post a product after deleting one,
    and you're using auto-generated ID,
    the deleted product ID will not be used again
     */


}
