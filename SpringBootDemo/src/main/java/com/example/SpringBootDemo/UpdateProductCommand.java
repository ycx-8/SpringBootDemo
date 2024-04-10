package com.example.SpringBootDemo;

import com.example.SpringBootDemo.Product.Model.Product;
import lombok.Data;

@Data
public class UpdateProductCommand {

    private Integer id;
    private Product product;

    public UpdateProductCommand(Integer id, Product product) {
        this.id = id;
        this.product = product;
    }
    public UpdateProductCommand(Integer id) {
        this.id = id;
    }
}
