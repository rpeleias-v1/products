package com.rodrigopeleias.products.service;

import com.rodrigopeleias.products.domain.Product;
import com.rodrigopeleias.products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Product update(Product product) {
        return productRepository.save(product);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long productId) {
        return productRepository.findOne(productId);
    }



    public void delete(Long productId) {
        if (productRepository.exists(productId)) {
            productRepository.delete(productId);
        }
    }
}
