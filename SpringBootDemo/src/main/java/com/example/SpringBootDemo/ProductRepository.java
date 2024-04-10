package com.example.SpringBootDemo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.SpringBootDemo.Product.Model.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
