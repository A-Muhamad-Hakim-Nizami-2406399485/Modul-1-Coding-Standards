package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class ProductRepository {
    private final List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        if (product.getProductId() == null || product.getProductId().isBlank()) {
            product.setProductId(UUID.randomUUID().toString());
        }
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public Optional<Product> findById(String id) {
        return productData.stream()
                .filter(p -> p.getProductId() != null && p.getProductId().equals(id))
                .findFirst();
    }

    public Product update(Product product) {
        if (product.getProductId() == null) {
            throw new IllegalArgumentException("Product id is required for update");
        }
        Optional<Product> existingOpt = findById(product.getProductId());
        if (existingOpt.isPresent()) {
            Product existing = existingOpt.get();
            existing.setProductName(product.getProductName());
            existing.setProductQuantity(product.getProductQuantity());
            return existing;
        }

        return create(product);
    }

    public boolean delete(String id) {
        return productData.removeIf(p -> p.getProductId() != null && p.getProductId().equals(id));
    }
}
