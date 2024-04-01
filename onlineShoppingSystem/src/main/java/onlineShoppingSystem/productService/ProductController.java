package onlineShoppingSystem.productService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) throws ProductNotFoundException {
        Product product = productService.getProductById(id);
        if (product == null) {
            throw new ProductNotFoundException("Product not found with id: " + id);
        }
        return ResponseEntity.ok(product);
    }
}