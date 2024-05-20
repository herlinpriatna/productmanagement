package com.example.product;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class ProductServiceTest {
    private ProductService productService;

    @Before
    public void setUp() {
        productService = new ProductService();
    }

    @Test
    public void testCreateProduct() {
        productService.createProduct("Laptop", 1500.0, 10);
        List<Product> products = productService.listProducts();
        assertEquals(1, products.size());
        Product product = products.get(0);
        assertEquals("1", product.getId());
        assertEquals("Laptop", product.getName());
        assertEquals(1500.0, product.getPrice(), 0.001);
        assertEquals(10, product.getQuantity());
    }

    @Test
    public void testListProducts() {
        productService.createProduct("Laptop", 1500.0, 10);
        productService.createProduct("Mouse", 25.0, 100);
        List<Product> products = productService.listProducts();
        assertEquals(2, products.size());
    }

    @Test
    public void testGetProduct() {
        productService.createProduct("Laptop", 1500.0, 10);
        Optional<Product> productOpt = productService.getProduct("1");
        assertTrue(productOpt.isPresent());
        Product product = productOpt.get();
        assertEquals("Laptop", product.getName());
        assertEquals(1500.0, product.getPrice(), 0.001);
        assertEquals(10, product.getQuantity());
    }

    @Test
    public void testUpdateProduct() {
        productService.createProduct("Laptop", 1500.0, 10);
        productService.updateProduct("1", "Gaming Laptop", 2000.0, 5);
        Optional<Product> productOpt = productService.getProduct("1");
        assertTrue(productOpt.isPresent());
        Product product = productOpt.get();
        assertEquals("Gaming Laptop", product.getName());
        assertEquals(2000.0, product.getPrice(), 0.001);
        assertEquals(5, product.getQuantity());
    }

    @Test
    public void testDeleteProduct() {
        productService.createProduct("Laptop", 1500.0, 10);
        productService.createProduct("Mouse", 25.0, 100);
        productService.deleteProduct("1");
        List<Product> products = productService.listProducts();
        assertEquals(1, products.size());
        assertEquals("2", products.get(0).getId());
    }

    @Test
    public void testSearchProductsByName() {
        productService.createProduct("Laptop", 1500.0, 10);
        productService.createProduct("Mouse", 25.0, 100);
        productService.createProduct("Laptop Case", 50.0, 20);

        List<Product> searchResults = productService.searchProductsByName("Laptop");
        assertEquals(2, searchResults.size());
        assertTrue(searchResults.stream().anyMatch(product -> product.getName().equals("Laptop")));
        assertTrue(searchResults.stream().anyMatch(product -> product.getName().equals("Laptop Case")));
    }
}
