package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class ProductRepository {
    // thread-safe list to hold in-memory products for this exercise
    private final List<Product> productData = new CopyOnWriteArrayList<>();

    public Product create(Product product) {
        // assign id if missing
        if (product.getProductId() == null || product.getProductId().isBlank()) {
            product.setProductId(UUID.randomUUID().toString());
        }
        productData.add(product);
        return product;
    }

    public List<Product> findAll() {
        // return a copy to avoid callers mutating the internal list
        return new ArrayList<>(productData);
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
