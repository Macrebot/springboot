package macrebot.practices.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import macrebot.practices.springboot.models.Product;
import macrebot.practices.springboot.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    // Instancia de clase
    // ProductService productService = new ProductServiceImpl();
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<?> getProducts() {
        List<Product> products = productService.getProducts();

        return ResponseEntity.ok(products);
    }

}
