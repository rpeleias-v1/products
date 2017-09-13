package com.rodrigopeleias.products.repository;

import com.rodrigopeleias.products.domain.Image;
import com.rodrigopeleias.products.domain.Product;
import com.rodrigopeleias.products.exception.ProductNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductRepositoryTest {

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
    public void shouldFindAllProductsWithImages() {
        Product product = new Product();
        product.setName("Computer");
        product.setDescription("A personal computer");
        productRepository.save(product);

        Image image = new Image();
        image.setType(".JPEG");
        image.setProduct(product);
        imageRepository.save(image);
        List<Product> productsWithImages = productRepository.findAllWithImages();

        assertThat(productsWithImages.size(), is(1));
        assertThat(productsWithImages.get(0).getImages().size(), is(1));
    }

    @Test
    public void shouldFindAllProductsWithoutImages() {
        Product product = new Product();
        product.setName("Computer");
        product.setDescription("A personal computer");
        productRepository.save(product);
        List<Product> products = productRepository.findAllWithImages();

        assertThat(products.size(), is(1));
        assertThat(products.get(0).getImages().size(), is(0));
    }

    @Test
    public void shouldFindAllProductsWithParentProduct() {
        Product product = new Product();
        product.setName("Computer");
        product.setDescription("A personal computer");
        product = productRepository.save(product);

        Product childProduct = new Product();
        childProduct.setName("Mouse");
        childProduct.setDescription("A computer mouse");
        childProduct.setParentProduct(product);
        productRepository.save(childProduct);

        List<Product> products = productRepository.findAllWithParentProduct();

        assertThat(products.size(), is(2));
        assertThat(products.get(1).getParentProduct(), notNullValue());
    }

    @Test
    public void shouldFindAllProductsWithoutParentProduct() {
        Product product = new Product();
        product.setName("Computer");
        product.setDescription("A personal computer");
        productRepository.save(product);
        List<Product> products = productRepository.findAllWithParentProduct();

        assertThat(products.size(), is(1));
        assertThat(products.get(0).getParentProduct(), nullValue());
    }

    @Test
    public void shouldFindAllProductsWithImagesAndParentProduct() {
        Product product = new Product();
        product.setName("Computer");
        product.setDescription("A personal computer");
        product = productRepository.save(product);

        Product childProduct = new Product();
        childProduct.setName("Mouse");
        childProduct.setDescription("A computer mouse");
        childProduct.setParentProduct(product);
        productRepository.save(childProduct);

        Image image = new Image();
        image.setType(".JPEG");
        image.setProduct(product);
        imageRepository.save(image);

        List<Product> productsWithImagesAndParentProducts = productRepository.findAllWithImagesAndParentProduct();

        assertThat(productsWithImagesAndParentProducts.size(), is(2));
        assertThat(productsWithImagesAndParentProducts.get(0).getImages().size(), is(1));
        assertThat(productsWithImagesAndParentProducts.get(1).getParentProduct(), notNullValue());
    }

    @Test
    public void shouldFindAllProductsWithoutImagesParentProduct() {
        Product product = new Product();
        product.setName("Computer");
        product.setDescription("A personal computer");
        productRepository.save(product);
        List<Product> products = productRepository.findAllWithImagesAndParentProduct();

        assertThat(products.size(), is(1));
        assertThat(products.get(0).getImages().size(), is(0));
        assertThat(products.get(0).getParentProduct(), nullValue());
    }

    @Test
    public void shouldFindByIdProductsWithImages() {
        Product product = new Product();
        product.setName("Computer");
        product.setDescription("A personal computer");
        product = productRepository.save(product);

        Image image = new Image();
        image.setType(".JPEG");
        image.setProduct(product);
        imageRepository.save(image);
        Product productWithImages = productRepository.findWithImagesByProductId(product.getId());

        assertThat(productWithImages, notNullValue());
        assertThat(productWithImages.getImages().size(), is(1));
    }

    @Test
    public void shouldFindByIdProductsWithoutImages() {
        Product product = new Product();
        product.setName("Computer");
        product.setDescription("A personal computer");
        product = productRepository.save(product);
        Product productWithoutImage = productRepository.findWithImagesByProductId(product.getId());

        assertThat(productWithoutImage, notNullValue());
        assertThat(productWithoutImage.getImages().size(), is(0));
    }

    @Test
    public void shouldFindProductByIdWithParentProduct() {
        Product product = new Product();
        product.setName("Computer");
        product.setDescription("A personal computer");
        product = productRepository.save(product);

        Product childProduct = new Product();
        childProduct.setName("Mouse");
        childProduct.setDescription("A computer mouse");
        childProduct.setParentProduct(product);
        productRepository.save(childProduct);

        product = productRepository.findWithParentProductByProductId(product.getId());

        assertThat(product, notNullValue());
        assertThat(childProduct.getParentProduct(), notNullValue());
    }

    @Test
    public void shouldFindProductByIdWithoutParentProduct() {
        Product product = new Product();
        product.setName("Computer");
        product.setDescription("A personal computer");
        product = productRepository.save(product);
        product = productRepository.findWithImagesByProductId(product.getId());

        assertThat(product, notNullValue());
        assertThat(product.getParentProduct(), nullValue());
    }

    @Test
    public void shouldFindProductByIdWithImagesAndParentProduct() {
        Product product = new Product();
        product.setName("Computer");
        product.setDescription("A personal computer");
        product = productRepository.save(product);

        Product childProduct = new Product();
        childProduct.setName("Mouse");
        childProduct.setDescription("A computer mouse");
        childProduct.setParentProduct(product);
        productRepository.save(childProduct);

        Image image = new Image();
        image.setType(".JPEG");
        image.setProduct(product);
        imageRepository.save(image);

        product = productRepository.findWithImagesAndParentProductByProductId(product.getId());

        assertThat(product, notNullValue());
        assertThat(product.getImages().size(), is(1));
        assertThat(childProduct.getParentProduct(), notNullValue());
    }

    @Test
    public void shouldFindParentProductByProductId() {
        Product product = new Product();
        product.setName("Computer");
        product.setDescription("A personal computer");
        product = productRepository.save(product);

        Product childProduct = new Product();
        childProduct.setName("Mouse");
        childProduct.setDescription("A computer mouse");
        childProduct.setParentProduct(product);
        productRepository.save(childProduct);
        Product parentProduct = productRepository.findParentProductsByProductId(childProduct.getId());

        assertThat(parentProduct, notNullValue());
    }
}
