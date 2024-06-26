# README

This is a Spring Boot demo.

## Set Up

**IDE**: IntelliJ

**DB**: MySQL

**API testing & documentation**: Postman

**Spring Initializer**: Lombok, Spring Web, Spring Data JPA, Spring Data JDBC, Devtools

1. Import spring project to IDE.
2. Add `mysql-connector-java` dependency manually through POM.
3. Connect MySQL server to IDE (see application.properties), then `CREATE DATABSE <name>`.
4. Run Hello World.

## Create Controllers

1. Set up sample controllers and test it via Postman.
2. Set up POJOs/models
3. Create tables in MySQL.
4. Create repository interface.

### Script to create tables and insert dummy data in MySQL Workbench

```sql
USE nobs;

CREATE TABLE product (
id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(255),
description VARCHAR(255),
price DOUBLE,
quantity INT
);

INSERT INTO product (name, description, price, quantity)
VALUES
  ('Product 1', 'Description for Product 1', 10.99, 100),
  ('Product 2', 'Description for Product 2', 19.99, 50);
```

## Command Query Responsibility Segregation (CQRS)

- A design pattern.
- **Command**: modifies state of the DB (create, update, delete). Will go in a Java class that has "command" in its name.
- **Query**: gets data from the DB (read). Will go in a Java class that has "Query" in its name.

### Command Class Structure

1. Invoke method(s) that validate input (may invoke them from a custom utility/validation class).
2. Invoke data repository method for any of create, update and delete action.
3. return `ResponseEntity.ok().build()`.

## Data Transfer Object (DTO)

- An object specifically used to send data over a network - use it for GET requests.
- Send fewer data over the network.
- Not sending data that could be sensitive.
- Not exposing the internal structure of the DB.
- Access control: same DB data, but send different responses based on user's role.
- Like a filter that filters what is being returned.

## Exception Handling

1. Add the custom exceptions to input validation.
2. Create a `GlobalExceptionHandler` to handle multiple customized exceptions.
3. Create class `SimpleResponse` so the exceptions return JSON rather than a string.
4. Create `CustomBaseException`.
5. Create custom Exception classes that extends `CustomBaseException`.

## Unit Testing

- Use `SpringBootTest` annotation.
- Make sure my unit tests fail first, before making it pass.
- Install maven and run `mvn test` to run all tests.
- Use Lombok's AllArgs constructur annotation to create objects.
- Arrange, Act, Assert

## Layer of Abstraction for Data Access

1. Relational database.
2. JDBC: network connection to DB.
3. JPA: a standard (abstract) that defines how a Java object is mapped to a relational DB.
4. Hibernate: implementation of JPA (ORM: Object Relational Mapper).
5. Spring Data JPA: allows default JPA methods like findById to be customized. e.g., findByAge.
6. Java program

### Custom Queries at a Repository Level

```java
import com.example.SpringBootDemo.Product.Model.Product;
import org.springframework.data.jpa.repository.Query;

@Query("SELECT p FROM product p WHERE p.price < :maxPrice")
List<Product> findProductWithPriceLessThan();
```

- Alternative to having a DTO layer (memory costly) - getting data directly filtered through SQL queries is more efficient.
- **Use Spring Data JPA to avoid writing any SQL queries.** Use keywords e.g., `findByDescriptionContaining(String str)` or `findByPriceLessThanOrderByPriceAsc(Double maxPrice)`.