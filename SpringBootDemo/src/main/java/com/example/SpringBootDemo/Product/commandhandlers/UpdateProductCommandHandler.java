package com.example.SpringBootDemo.Product.commandhandlers;

import com.example.SpringBootDemo.Command;
import com.example.SpringBootDemo.Exceptions.ProductNotFoundException;
import com.example.SpringBootDemo.Product.Model.Product;
import com.example.SpringBootDemo.Product.validation.ServiceValidation;
import com.example.SpringBootDemo.ProductRepository;
import com.example.SpringBootDemo.UpdateProductCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateProductCommandHandler implements Command<UpdateProductCommand, ResponseEntity> {

    @Autowired
    private ProductRepository productRepository;
    @Override
    public ResponseEntity<ResponseEntity> execute(UpdateProductCommand command) {
        Optional<Product> optionalProduct = productRepository.findById(command.getId());
        if (optionalProduct.isEmpty()) {
            throw new ProductNotFoundException();
        }
        Product product = command.getProduct();
        product.setId(command.getId());
        ServiceValidation.validateInput(product);
        productRepository.save(product);
        return ResponseEntity.ok().build();
    }
}
