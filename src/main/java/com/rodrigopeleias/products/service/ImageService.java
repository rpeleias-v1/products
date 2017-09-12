package com.rodrigopeleias.products.service;

import com.rodrigopeleias.products.domain.Image;
import com.rodrigopeleias.products.domain.Product;
import com.rodrigopeleias.products.exception.ImageNotFoundException;
import com.rodrigopeleias.products.exception.ProductNotFoundException;
import com.rodrigopeleias.products.repository.ImageRepository;
import com.rodrigopeleias.products.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ImageRepository imageRepository;

    public Image save(Long productId, Image image) {
        Product product = findProductOrThrowException(productId);
        image.setProduct(product);
        return imageRepository.save(image);
    }

    public Image update(Long productId, Long imageId, Image image) {
        findProductOrThrowException(productId);
        Image savedImage = findImageOrThrowException(imageId);
        BeanUtils.copyProperties(image, savedImage);
        return imageRepository.save(savedImage);
    }

    public void delete(Long productId, Long imageId) {
        findProductOrThrowException(productId);
        findImageOrThrowException(imageId);
        imageRepository.delete(imageId);
    }

    private Product findProductOrThrowException(Long productId) {
        Product savedProduct = productRepository.findOne(productId);
        if (savedProduct == null) {
            throw new ProductNotFoundException(productId.toString());
        }
        return savedProduct;
    }

    private Image findImageOrThrowException(Long imageId) {
        Image savedImage = imageRepository.findOne(imageId);
        if (savedImage == null) {
            throw new ImageNotFoundException(imageId.toString());
        }
        return savedImage;
    }
}
