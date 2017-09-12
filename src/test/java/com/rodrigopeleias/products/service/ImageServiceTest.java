package com.rodrigopeleias.products.service;

import com.rodrigopeleias.products.domain.Image;
import com.rodrigopeleias.products.domain.Product;
import com.rodrigopeleias.products.exception.ImageNotFoundException;
import com.rodrigopeleias.products.exception.ProductNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImageServiceTest {

    @Autowired
    private ImageService imageService;

    @Autowired
    private ProductService productService;

    private Product savedProduct;

    @Before
    public void setup() {
        Product product = new Product();
        product.setName("New product");
        product.setDescription("Description");

        savedProduct = productService.save(product);
    }

    @Test
    public void shouldCreateImageAssociatedWithProduct() {
        Image image = new Image();
        image.setType(".JPEG");
        Image savedImage = imageService.save(this.savedProduct.getId(), image);
        assertThat(savedImage.getId(), notNullValue());
        assertThat(savedImage.getType(), equalTo(image.getType()));
        assertThat(savedImage.getProduct(), notNullValue());
    }

    @Test(expected = ProductNotFoundException.class)
    public void shouldThrowExceptionForNonExistingProduct() {
        Image image = new Image();
        image.setType(".JPEG");
        imageService.save(20L, image);
    }

    @Test
    public void shouldUpdateAnExistingImage() {
        Image image = new Image();
        image.setType(".JPEG");

        Image savedImage = imageService.save(this.savedProduct.getId(), image);
        savedImage.setType(".PNG");

        Image updatedImage = imageService.update(savedImage.getId(), savedProduct.getId(), savedImage);
        assertThat(updatedImage.getId(), notNullValue());
        assertThat(updatedImage.getType(), equalTo(savedImage.getType()));
        assertThat(updatedImage.getProduct(), notNullValue());
    }

    @Test(expected = ProductNotFoundException.class)
    public void shouldThrowExceptionWhenUpdateANonExistingProduct() {
        Image image = new Image();
        image.setType(".JPEG");

        Image savedImage = imageService.save(20L, image);
        savedImage.setType(".PNG");

        imageService.update(savedImage.getId(), savedProduct.getId(), savedImage);
    }

    @Test(expected = ImageNotFoundException.class)
    public void shouldThrowExceptionWhenUpdateANonExistingImage() {
        Image image = new Image();
        image.setId(20L);
        image.setType(".JPEG");

        imageService.update(savedProduct.getId(), image.getId(), image);
    }
}
