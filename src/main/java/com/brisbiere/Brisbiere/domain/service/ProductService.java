package com.brisbiere.Brisbiere.domain.service;

import com.brisbiere.Brisbiere.domain.Product;
import com.brisbiere.Brisbiere.domain.repository.ProductRepository;
import com.brisbiere.Brisbiere.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    public List<Product> getAll(){
        return productRepository.getAll();
    }

    public Optional<List<Product>> getByBrand(String brand){
        return productRepository.getByBrand(brand);
    }

    public Optional<List<Product>> getByStyle(String style){
        return productRepository.getByStyle(style);
    }

    public Optional<Page<Product>> getAvailable(int page, int elements, String sortBy, String sortDirection){
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageRequest = PageRequest.of(page, elements, sort);

        return productRepository.findBy(pageRequest);
    }

    public Optional<Product> getProduct(int productId){
        return productRepository.getProduct(productId);
    }

    public Product save(Product product){
        return productRepository.save(product);
    }

    public Boolean exists(int productId){
        return this.productRepository.exists(productId);
    }

    @Secured("ROLE_ADMIN")
    public boolean delete(int productId){
        return getProduct(productId).map(product -> {
            productRepository.delete(productId);
            return true;
        }).orElse(false);
    }
}

