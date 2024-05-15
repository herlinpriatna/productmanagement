package com.example.product;

import org.junit.Before;
import org.junit.Test;
import java.util.Optional;
import static org.junit.Assert.*;

public class ProductServiceTest {
    private ProductService service;

    @Before
    public void setUp() {
        service = new ProductService();
        service.createProduct("1", "Laptop", 1500.0, 10);
        service.createProduct("2", "Mouse", 25.0, 100);
    }

    @Test
    public void testCreateAndListProducts() {
        assertEquals(2, service.listProducts().size());
    }

    @Test
    public void testGetProduct() {
        Optional<Product> product = service.getProduct("1");
        assertTrue(product.isPresent());
        assertEquals("Laptop", product.get().getName());
    }

    @Test
    public void testUpdateProduct() {
        service.updateProduct("2", "Wireless Mouse", 30.0, 80);
        Optional<Product> product = service.getProduct("2");
        assertTrue(product.isPresent());
        assertEquals("Wireless Mouse", product.get().getName());
        assertEquals(30.0, product.get().getPrice(), 0);
        assertEquals(80, product.get().getQuantity());
    }

    @Test
    public void testDeleteProduct() {
        service.deleteProduct("1");
        assertEquals(1, service.listProducts().size());
        assertFalse(service.getProduct("1").isPresent());
    }
}
