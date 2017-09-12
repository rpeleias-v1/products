package com.rodrigopeleias.products.service;

import com.rodrigopeleias.products.domain.Product;
import com.rodrigopeleias.products.exception.ProductNotFoundException;
import com.rodrigopeleias.products.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
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

    public Product update(Long productId, Product product) {
        Product savedProduct = findProductOrThrowException(productId);
        BeanUtils.copyProperties(product, savedProduct);
        return productRepository.save(savedProduct);
    }

    public void delete(Long productId) {
        findProductOrThrowException(productId);
        productRepository.delete(productId);
    }

    private Product findProductOrThrowException(Long productId) {
        Product savedProduct = productRepository.findOne(productId);
        if (savedProduct == null) {
            throw new ProductNotFoundException(productId.toString());
        }
        return savedProduct;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long productId) {
        return productRepository.findOne(productId);
    }

}
