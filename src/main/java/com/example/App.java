package com.example;

import com.example.product.ProductService;

public class App {
    /**
     * @param args
     */
    public static void main(String[] args) {
        ProductService service = new ProductService();

        service.createProduct("1", "Laptop", 1500.0, 10);
        service.createProduct("2", "Mouse", 25.0, 100);

        System.out.println("All Products:");
        System.out.println(service.listProducts());

        System.out.println("\nGet Product with ID 1:");
        System.out.println(service.getProduct("1").orElse(null));

        System.out.println("\nUpdate Product with ID 2:");
        service.updateProduct("2", "Wireless Mouse", 30.0, 80);
        System.out.println(service.getProduct("2").orElse(null));

        System.out.println("\nDelete Product with ID 1:");
        service.deleteProduct("1");
        System.out.println(service.listProducts());
    }
}
