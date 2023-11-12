package com.brisbiere.Brisbiere.domain.repository;

import com.brisbiere.Brisbiere.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> getAll();
    Optional<List<Product>> getByBrand(String brand);
    Optional<List<Product>> getByStyle(String style);
    Optional<Page<Product>> findBy(Pageable pageable);
    Optional<Product> getProduct(int productId);
    Product save(Product product);
    void delete(int productId);
    boolean exists(int productId);
}
