package com.example.SpringBootDemo.Product.validation;

import com.example.SpringBootDemo.Product.Model.Product;
import com.example.SpringBootDemo.ProductRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class ServiceValidation {

    @Autowired
    private ProductRepository productRepository;

    public static void validateInput(Product product) {
        if (StringUtils.isBlank(product.getName())) {
            throw new RuntimeException("Name cannot be empty/null");
        }
        if (StringUtils.isBlank(product.getDescription())) {
            throw new RuntimeException("Description cannot be empty/null");
        }
        if (product.getPrice() <= 0.0) {
            throw new RuntimeException("Price cannot be smaller than or equals to 0.0");
        }
        if (product.getQuantity() <= 0) {
            throw new RuntimeException("Quantity cannot be smaller than or equals to 0");
        }
    }
}
