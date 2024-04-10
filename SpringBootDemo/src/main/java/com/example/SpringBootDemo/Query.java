package com.example.SpringBootDemo;

import com.example.SpringBootDemo.Product.Model.Product;
import org.springframework.http.ResponseEntity;

public interface Query <I, O>{
    ResponseEntity<O> execute(I input);

}
