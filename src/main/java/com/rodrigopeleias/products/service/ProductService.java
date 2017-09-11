package com.rodrigopeleias.products.service;

import com.rodrigopeleias.products.domain.Product;
import com.rodrigopeleias.products.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Product update(Long productId, Product product) {
        Product savedProduct = productRepository.findOne(productId);
        BeanUtils.copyProperties(product, savedProduct);
        return productRepository.save(savedProduct);
    }

    public void delete(Long productId) {
        if (productRepository.exists(productId)) {
            productRepository.delete(productId);
        }
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long productId) {
        return productRepository.findOne(productId);
    }

}
