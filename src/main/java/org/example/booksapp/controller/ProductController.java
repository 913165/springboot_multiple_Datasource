package org.example.booksapp.controller;

import org.example.booksapp.model.Product;
import org.example.booksapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
        return productService.getProductById(id);
    }

    // Add the addProduct, updateProduct, and deleteProduct methods here
    @PostMapping("/products")
    public void addProduct(@RequestBody  Product product) {
        productService.addProduct(product);
    }

    @PutMapping("/products/{id}")
    public void updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        product.setId(id);
        productService.updateProduct(product);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
    }
}
