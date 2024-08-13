package org.example.booksapp.repository;

import org.example.booksapp.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.util.List;

@Component
public class ProductDAO {
    @Autowired
    @Qualifier("psdatasource")
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    // getAllProducts
    public List<Product> getAllProducts() {
        return jdbcTemplate.query("select * from products", (rs, rowNum) ->
                new Product(rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price")));
    }

    // get product by id
    public Product getProductById(Long id) {
        return jdbcTemplate.queryForObject("select * from products where id = ?", new Object[]{id}, (rs, rowNum) ->
                new Product(rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price")));
    }

    // add product
    public Product addProduct(Product product) {
        jdbcTemplate.update("insert into products (name, description, price) values (?, ?, ?)",
                product.getName(), product.getDescription(), product.getPrice());
        return product;
    }

    // update product
    public Product updateProduct(Product product) {
        jdbcTemplate.update("update products set name = ?, description = ?, price = ? where id = ?",
                product.getName(), product.getDescription(), product.getPrice(), product.getId());
        return product;
    }

    // delete product by id
    public void deleteProduct(Long id) {
        jdbcTemplate.update("delete from products where id = ?", id);
    }

}
