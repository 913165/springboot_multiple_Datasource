package org.example.booksapp.service;

import org.example.booksapp.model.Product;
import org.example.booksapp.repository.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductDAO productDAO;

    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    public Product getProductById(Long id) {
        return productDAO.getProductById(id);
    }

    public Product addProduct(Product product) {
        return productDAO.addProduct(product);
    }

    public Product updateProduct(Product product) {
        return productDAO.updateProduct(product);
    }

    public void deleteProduct(Long id) {
        productDAO.deleteProduct(id);
    }
}
