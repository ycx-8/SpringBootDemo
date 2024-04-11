package com.example.SpringBootDemo.Product.queryhandlers;

import com.example.SpringBootDemo.Exceptions.ProductNotFoundException;
import com.example.SpringBootDemo.Product.Model.Product;
import com.example.SpringBootDemo.Product.Model.ProductDTO;
import com.example.SpringBootDemo.ProductRepository;
import com.example.SpringBootDemo.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GetProductQueryHandler implements Query<Integer, ProductDTO> {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ResponseEntity<ProductDTO> execute(Integer id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new ProductNotFoundException();
        }

        return ResponseEntity.ok(new ProductDTO(product.get()));
    }

    public ResponseEntity<Product> findProductByName(String name) {
        return ResponseEntity.ok(productRepository.findByName(name));
    }
}
