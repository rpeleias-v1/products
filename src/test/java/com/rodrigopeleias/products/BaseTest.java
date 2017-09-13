package com.rodrigopeleias.products;

import com.rodrigopeleias.products.repository.ImageRepository;
import com.rodrigopeleias.products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseTest {

    @Autowired
    protected ImageRepository imageRepository;

    @Autowired
    protected ProductRepository productRepository;

    public void setUp() {
        imageRepository.deleteAll();
        productRepository.deleteAll();
    }
}
