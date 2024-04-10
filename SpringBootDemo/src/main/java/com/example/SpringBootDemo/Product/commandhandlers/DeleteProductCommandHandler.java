package com.example.SpringBootDemo.Product.commandhandlers;

import com.example.SpringBootDemo.Command;
import com.example.SpringBootDemo.Exceptions.ProductNotFoundException;
import com.example.SpringBootDemo.Product.Model.Product;
import com.example.SpringBootDemo.ProductRepository;
import com.example.SpringBootDemo.UpdateProductCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteProductCommandHandler implements Command<Integer, ResponseEntity> {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ResponseEntity<ResponseEntity> execute(Integer id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException();
        }
        productRepository.delete(productOptional.get());
        return ResponseEntity.ok().build();
    }
}
