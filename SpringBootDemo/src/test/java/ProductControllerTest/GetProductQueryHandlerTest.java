package ProductControllerTest;

import com.example.SpringBootDemo.Exceptions.ProductNotFoundException;
import com.example.SpringBootDemo.Product.Model.Product;
import com.example.SpringBootDemo.Product.Model.ProductDTO;
import com.example.SpringBootDemo.Product.queryhandlers.GetProductQueryHandler;
import com.example.SpringBootDemo.ProductRepository;
import com.example.SpringBootDemo.SpringBootDemoApplication;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = SpringBootDemoApplication.class)
public class GetProductQueryHandlerTest {

    @InjectMocks
    private GetProductQueryHandler getProductQueryHandler;

    @Mock
    private ProductRepository productRepository;

    @Test
    public void getProductQueryHandler_validId_returns_productDTIO() {
        Product product = new Product();
        product.setId(1);
        product.setName("a product");
        product.setDescription("a random product");
        product.setPrice(2.50);
        product.setQuantity(5);
        ProductDTO expectedDTO = new ProductDTO(product);
        // Stubbing
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        ResponseEntity<ProductDTO> productDTIO = getProductQueryHandler.execute(product.getId());
        assertEquals(expectedDTO, productDTIO.getBody());
        assertEquals(HttpStatus.OK, productDTIO.getStatusCode());
    }

    @Test
    public void getProductQueryHandler_invalidId_throwsProductNotFoundException() {
        when(productRepository.findById(1)).thenReturn(Optional.empty());
        ProductNotFoundException productNotFoundException = assertThrows(ProductNotFoundException.class, () -> getProductQueryHandler.execute(1));
        assertEquals("Product not found", productNotFoundException.getSimpleResponse().getMessage());
    }
}
