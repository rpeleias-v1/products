package com.rodrigopeleias.products.repository;

import com.rodrigopeleias.products.domain.Image;
import com.rodrigopeleias.products.domain.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImageRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Before
    public void setUp() {
        imageRepository.deleteAll();
        productRepository.deleteAll();
    }

    @Test
    public void shouldFindImagesByProduct() {
        Product product = new Product();
        product.setName("Computer");
        product.setDescription("A personal computer");
        product = productRepository.save(product);

        Image jpeg = new Image();
        jpeg.setType(".JPEG");
        jpeg.setProduct(product);
        imageRepository.save(jpeg);

        Image png = new Image();
        png.setType(".PNG");
        png.setProduct(product);
        imageRepository.save(png);

        List<Image> images = imageRepository.findByProduct(product);

        assertThat(images.size(), is(2));
    }
}
