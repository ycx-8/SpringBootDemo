package ProductControllerTest;

import com.example.SpringBootDemo.Product.commandhandlers.CreateProductCommandHandler;
import com.example.SpringBootDemo.SpringBootDemoApplication;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = SpringBootDemoApplication.class)
public class CreateProductCommandHandlerTest {
    @InjectMocks
    private CreateProductCommandHandler createProductCommandHandler;

    
}
