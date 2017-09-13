package com.rodrigopeleias.products.service;

import com.rodrigopeleias.products.BaseTest;
import com.rodrigopeleias.products.domain.Image;
import com.rodrigopeleias.products.domain.Product;
import com.rodrigopeleias.products.exception.ProductNotFoundException;
import com.rodrigopeleias.products.repository.ImageRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest extends BaseTest{

    @Autowired
    private ProductService productService;

    @Autowired
    private ImageRepository imageRepository;

    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void shouldCreateProduct() {
        Product product = new Product();
        product.setName("New product");
        product.setDescription("Description");

        Product savedProduct = productService.save(product);
        assertThat(savedProduct.getId(), notNullValue());
        assertThat(savedProduct.getName(), equalTo(product.getName()));
        assertThat(savedProduct.getDescription(), equalTo(product.getDescription()));
    }

    @Test
    public void shouldUpdateAnExistingProduct() {
        Product product = new Product();
        product.setName("Update test");
        product.setDescription("Description");

        Product savedProduct = productService.save(product);
        savedProduct.setName("New name");
        savedProduct.setDescription("New description");

        Product updatedProduct = productService.update(savedProduct.getId(), savedProduct);
        assertThat(updatedProduct.getId(), notNullValue());
        assertThat(updatedProduct.getName(), equalTo(savedProduct.getName()));
        assertThat(updatedProduct.getDescription(), equalTo(savedProduct.getDescription()));
    }

    @Test(expected = ProductNotFoundException.class)
    public void shouldThrowExceptionWhenUpdateANonExistingProduct() {
        Product product = new Product();
        product.setName("Update test");
        product.setDescription("Description");
        productService.update(200L, product);
    }

    @Test
    public void shouldDeleteAnExistingProduct() {
        Product product = new Product();
        product.setName("Delete test");
        product.setDescription("Description");

        Product savedProduct = productService.save(product);
        productService.delete(savedProduct.getId());

        productService.findById(product.getId());
    }

    @Test
    public void shouldFindAllProducts() {
        Product product = new Product();
        product.setName("New product");
        product.setDescription("Description");

        Product computer = new Product();
        computer.setName("A computer");
        computer.setDescription("A personal computer");

        productService.save(product);
        productService.save(computer);

        List<Product> products = productService.findAll();
        assertThat(products.size(), is(2));
    }

    @Test
    public void shouldFindAllProductsWithImages() {
        Product product = new Product();
        product.setName("New product");
        product.setDescription("Description");
        productService.save(product);

        Image jpeg = new Image();
        jpeg.setType(".JPEG");
        jpeg.setProduct(product);
        imageRepository.save(jpeg);

        List<Product> products = productService.findAllWithImages();
        assertThat(products.size(), is(1));
        assertThat(products.get(0).getImages().size(), is(1));
    }

    @Test
    public void shouldFindAllProductsWithParentProduct() {
        Product product = new Product();
        product.setName("Computer");
        product.setDescription("A desktop computer");
        productService.save(product);

        Product childProduct = new Product();
        childProduct.setName("Mouse");
        childProduct.setDescription("A computer mouse");
        childProduct.setParentProduct(product);
        productService.save(childProduct);

        List<Product> products = productService.findAllWithChildProducts();
        assertThat(products.size(), is(2));
        assertThat(products.get(1).getParentProduct(),notNullValue());
    }

    @Test
    public void shouldFindAllProductsWithImagesAndParentProduct() {
        Product product = new Product();
        product.setName("Computer");
        product.setDescription("A desktop computer");
        productService.save(product);

        Image jpeg = new Image();
        jpeg.setType(".JPEG");
        jpeg.setProduct(product);
        imageRepository.save(jpeg);

        Product childProduct = new Product();
        childProduct.setName("Mouse");
        childProduct.setDescription("A computer mouse");
        childProduct.setParentProduct(product);
        productService.save(childProduct);

        List<Product> products = productService.findAllWithImagesAndChildProducts();
        assertThat(products.size(), is(2));
        assertThat(products.get(1).getParentProduct(),notNullValue());
        assertThat(products.get(0).getImages().size(), is(1));
    }

    @Test
    public void shouldFindProductByIdWithImages() {
        Product product = new Product();
        product.setName("New product");
        product.setDescription("Description");
        product =  productService.save(product);

        Image jpeg = new Image();
        jpeg.setType(".JPEG");
        jpeg.setProduct(product);
        imageRepository.save(jpeg);

        Product foundProduct = productService.findWithImagesByProductId(product.getId());
        assertThat(foundProduct, notNullValue());
        assertThat(foundProduct.getImages().size(), is(1));
    }

    @Test(expected = ProductNotFoundException.class)
    public void shouldThrowExceptionWhenFindInvalidProductWithImages() {
        productService.findWithImagesByProductId(1000L);
    }

    @Test
    public void shouldFindProductByIdWithParentProduct() {
        Product product = new Product();
        product.setName("Computer");
        product.setDescription("A desktop computer");
        product = productService.save(product);

        Product childProduct = new Product();
        childProduct.setName("Mouse");
        childProduct.setDescription("A computer mouse");
        childProduct.setParentProduct(product);
        productService.save(childProduct);

        Product savedProduct = productService.findWithChildProductsByProductId(childProduct.getId());
        assertThat(savedProduct, notNullValue());
        assertThat(savedProduct.getParentProduct(),notNullValue());
    }

    @Test(expected = ProductNotFoundException.class)
    public void shouldThrowExceptionWhenFindInvalidProductWithParentProduct() {
        productService.findWithChildProductsByProductId(1000L);
    }

    @Test
    public void shouldFindProductByIdWithImagesAndParentProduct() {
        Product product = new Product();
        product.setName("Computer");
        product.setDescription("A desktop computer");
        product = productService.save(product);

        Product childProduct = new Product();
        childProduct.setName("Mouse");
        childProduct.setDescription("A computer mouse");
        childProduct.setParentProduct(product);
        productService.save(childProduct);

        Image jpeg = new Image();
        jpeg.setType(".JPEG");
        jpeg.setProduct(childProduct);
        imageRepository.save(jpeg);

        Product savedProduct = productService.findWithImagesAndChildProductsByProductId(childProduct.getId());
        assertThat(savedProduct, notNullValue());
        assertThat(savedProduct.getParentProduct(),notNullValue());
        assertThat(savedProduct.getImages().size(), is(1));
    }

    @Test(expected = ProductNotFoundException.class)
    public void shouldThrowExceptionWhenFindInvalidProductWithImagesAndParentProduct() {
        productService.findWithImagesAndChildProductsByProductId(1000L);
    }

    @Test
    public void shouldFindParentProductByProductId() {
        Product product = new Product();
        product.setName("Computer");
        product.setDescription("A desktop computer");
        product = productService.save(product);

        Product childProduct = new Product();
        childProduct.setName("Mouse");
        childProduct.setDescription("A computer mouse");
        childProduct.setParentProduct(product);
        productService.save(childProduct);

        List<Product> childProducts = productService.findChildProductsByProductId(product.getId());

        assertThat(childProducts.size(), is(1));
    }

    @Test(expected = ProductNotFoundException.class)
    public void shouldThrowExceptionWhenFindParentProductByInvalidProductId() {
        productService.findChildProductsByProductId(1000L);
    }


}
