package com.rodrigopeleias.products.service;

import com.rodrigopeleias.products.domain.Product;
import com.rodrigopeleias.products.dto.ProductDTO;
import com.rodrigopeleias.products.exception.ProductNotFoundException;
import com.rodrigopeleias.products.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    public List<ProductDTO> findAll() {
        List<ProductDTO> productsDTO = new ArrayList<>();
        List<Product> products = productRepository.findAll();
        products.forEach(product -> {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(product.getId());
            productDTO.setName(product.getName());
            productDTO.setDescription(product.getDescription());
            productsDTO.add(productDTO);
        });
        return productsDTO;
    }

    public ProductDTO findById(Long productId) {
        Product product = productRepository.findOne(productId);
        if (product == null) {
            throw new ProductNotFoundException(productId.toString());
        }
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        return productDTO;
    }

}
