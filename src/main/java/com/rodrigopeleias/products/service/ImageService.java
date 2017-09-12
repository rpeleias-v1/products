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
    private ProductService productService;

    @Autowired
    private ImageRepository imageRepository;

    public Image save(Long productId, Image image) {
        Product product = productService.findProductOrThrowException(productId);
        image.setProduct(product);
        return imageRepository.save(image);
    }

    public Image update(Long productId, Long imageId, Image image) {
        productService.findProductOrThrowException(productId);
        Image savedImage = findImageOrThrowException(imageId);
        BeanUtils.copyProperties(image, savedImage);
        return imageRepository.save(savedImage);
    }

    public void delete(Long productId, Long imageId) {
        productService.findProductOrThrowException(productId);
        findImageOrThrowException(imageId);
        imageRepository.delete(imageId);
    }

    public List<Image> findByProduct(Long productId) {
        Product product = productService.findProductOrThrowException(productId);
        return imageRepository.findByProduct(product);
    }

    private Image findImageOrThrowException(Long imageId) {
        Image savedImage = imageRepository.findOne(imageId);
        if (savedImage == null) {
            throw new ImageNotFoundException(imageId.toString());
        }
        return savedImage;
    }
}
