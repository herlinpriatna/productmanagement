package com.example.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductService {
    private List<Product> products = new ArrayList<>();

    public void createProduct(String id, String name, double price, int quantity) {
        products.add(new Product(id, name, price, quantity));
    }

    public List<Product> listProducts() {
        return products;
    }

    public Optional<Product> getProduct(String id) {
        return products.stream().filter(product -> product.getId().equals(id)).findFirst();
    }

    public void updateProduct(String id, String name, double price, int quantity) {
        getProduct(id).ifPresent(product -> {
            product.setName(name);
            product.setPrice(price);
            product.setQuantity(quantity);
        });
    }

    public void deleteProduct(String id) {
        products.removeIf(product -> product.getId().equals(id));
    }
}
