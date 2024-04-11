package com.example.SpringBootDemo;

import com.example.SpringBootDemo.Product.Model.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import com.example.SpringBootDemo.Product.Model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p WHERE p.price < :maxPrice")
    List<Product> findProductsWithPriceLessThan(@Param("maxPrice") double maxPrice);

    /**
     * Follow the query syntax: @Query("SELECT [Path.Class(param)] ...") to retrieve the right POJO.<br>
     * To avoid using DTO (since it's memory costly), we can just use a custom SQL query to return the desired objects.<br>
     * When nativeQuery = true, it means that the query is database agnostic - only works with MySQL in this case (since we are working with it). But you have to change the query syntax altogether. <br>
     * @return A List of ProductDTO
     */
    @Query("SELECT new com.example.SpringBootDemo.Product.Model.ProductDTO(p.name, p.description, p.price) FROM Product p")
    List<ProductDTO> getAllProductDTOs();

    /**
     * Example of using Spring Data JPA to retrieve an object.
     * @param name
     * @return a ProductDTO
     */
    Product findByName(String name);
}
