package com.rodrigopeleias.products.service;

import com.rodrigopeleias.products.domain.Product;
import com.rodrigopeleias.products.exception.ProductNotFoundException;
import com.rodrigopeleias.products.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
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

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long productId) {
        return productRepository.findOne(productId);
    }

    public List<Product> findAllWithImages() {
        return productRepository.findAllWithImages();
    }

    public List<Product> findAllWithParentProduct() {
        return productRepository.findAllWithParentProduct();
    }

    public List<Product> findAllWithImagesAndParentProduct() {
        return productRepository.findAllWithImagesAndParentProduct();
    }

    public Product findWithImagesByProductId(Long productId) {
        Product savedProduct = findProductOrThrowException(productId);
        return productRepository.findWithImagesByProductId(savedProduct.getId());
    }

    public Product findWithParentProductByProductId(Long productId) {
        Product savedProduct = findProductOrThrowException(productId);
        return productRepository.findWithParentProductByProductId(savedProduct.getId());
    }

    public Product findWithImagesAndParentProductByProductId(Long productId) {
        Product savedProduct = findProductOrThrowException(productId);
        return productRepository.findWithImagesAndParentProductByProductId(savedProduct.getId());
    }

    public Product findParentProductByProductId(Long productId) {
        Product savedProduct = findProductOrThrowException(productId);
        return productRepository.findParentProductByProductId(savedProduct.getId());
    }

    Product findProductOrThrowException(Long productId) {
        Product savedProduct = productRepository.findOne(productId);
        if (savedProduct == null) {
            throw new ProductNotFoundException(productId.toString());
        }
        return savedProduct;
    }
}
