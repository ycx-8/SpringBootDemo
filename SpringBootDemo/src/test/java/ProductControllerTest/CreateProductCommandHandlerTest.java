package ProductControllerTest;

import com.example.SpringBootDemo.Exceptions.ProductNotValidException;
import com.example.SpringBootDemo.Product.Model.Product;
import com.example.SpringBootDemo.Product.commandhandlers.CreateProductCommandHandler;
import com.example.SpringBootDemo.ProductRepository;
import com.example.SpringBootDemo.SpringBootDemoApplication;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = SpringBootDemoApplication.class)
public class CreateProductCommandHandlerTest {
    // What we are testing, we inject mocks.
    @InjectMocks
    private CreateProductCommandHandler createProductCommandHandler;

    @Mock
    private ProductRepository productRepository;

    // name: MethodName_StateUnderTest_ExpectedBehaviour
    @Test
    public void createProductCommandHandler_validProduct_returnsSuccess() {
        Product product = new Product();
        product.setId(1);
        product.setName("a product");
        product.setDescription("a random product");
        product.setPrice(2.50);
        product.setQuantity(5);
        ResponseEntity response = createProductCommandHandler.execute(product);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void createProductCommandHandler_invalidPrice_throwsInvalidPriceException() {
        Product product = new Product();
        product.setId(1);
        product.setName("a product");
        product.setDescription("a random product");
        product.setPrice(-2.50);
        product.setQuantity(5);
        ProductNotValidException productNotValidException = assertThrows(ProductNotValidException.class, () -> createProductCommandHandler.execute(product));
        assertEquals("Product Price cannot be smaller than or equals to 0.0", productNotValidException.getSimpleResponse().getMessage());
    }
}
