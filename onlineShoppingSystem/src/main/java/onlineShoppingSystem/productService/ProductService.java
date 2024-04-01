package onlineShoppingSystem.productService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product) {
        // Additional validation and business logic can be added here

        // Save product to repository
        return productRepository.save(product);
    }

    public Product getProductById(Long id) {
        return null;
    }

}