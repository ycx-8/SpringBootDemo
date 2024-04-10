package com.example.SpringBootDemo.Product.commandhandlers;

import com.example.SpringBootDemo.Command;
import com.example.SpringBootDemo.Product.validation.ServiceValidation;
import com.example.SpringBootDemo.ProductRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.SpringBootDemo.Product.Model.Product;

@Service
public class CreateProductCommandHandler implements Command<Product, ResponseEntity> {

    @Autowired
    private ProductRepository productRepository;
    @Override
    public ResponseEntity<ResponseEntity> execute(Product product) {

        ServiceValidation.validateInput(product);
        productRepository.save(product);
        return ResponseEntity.ok().build();
    }

}
