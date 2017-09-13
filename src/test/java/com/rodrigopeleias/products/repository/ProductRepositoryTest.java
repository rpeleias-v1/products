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
import static org.hamcrest.Matchers.*;

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
    public void shouldFindAllProductsWithChildProducts() {
        Product product = new Product();
        product.setName("Computer");
        product.setDescription("A personal computer");
        product = productRepository.save(product);

        Product childProduct = new Product();
        childProduct.setName("Mouse");
        childProduct.setDescription("A computer mouse");
        childProduct.setParentProduct(product);
        productRepository.save(childProduct);

        List<Product> products = productRepository.findAllWithChildProducts();

        assertThat(products.size(), is(2));
        assertThat(products.get(0).getChildProducts().size(), is(1));
    }

    @Test
    public void shouldFindAllProductsWithoutChildProduct() {
        Product product = new Product();
        product.setName("Computer");
        product.setDescription("A personal computer");
        productRepository.save(product);
        List<Product> products = productRepository.findAllWithChildProducts();

        assertThat(products.size(), is(1));
        assertThat(products.get(0).getChildProducts().size(), is(0));
    }

    @Test
    public void shouldFindAllProductsWithImagesAndChildProducts() {
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

        List<Product> products = productRepository.findAllWithImagesAndChildProducts();

        assertThat(products.size(), is(2));
        assertThat(products.get(0).getImages().size(), is(1));
        assertThat(products.get(0).getChildProducts().size(), is(1));
    }

    @Test
    public void shouldFindAllProductsWithoutImagesChildProducts() {
        Product product = new Product();
        product.setName("Computer");
        product.setDescription("A personal computer");
        productRepository.save(product);
        List<Product> products = productRepository.findAllWithImagesAndChildProducts();

        assertThat(products.size(), is(1));
        assertThat(products.get(0).getImages().size(), is(0));
        assertThat(products.get(0).getChildProducts().size(), is(0));
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
    public void shouldFindProductByIdWithChildProducts() {
        Product product = new Product();
        product.setName("Computer");
        product.setDescription("A personal computer");
        product = productRepository.save(product);

        Product childProduct = new Product();
        childProduct.setName("Mouse");
        childProduct.setDescription("A computer mouse");
        childProduct.setParentProduct(product);
        productRepository.save(childProduct);

        product = productRepository.findWithChildProductsByProductId(product.getId());

        assertThat(product, notNullValue());
        assertThat(product.getChildProducts().size(), is(1));
    }

    @Test
    public void shouldFindProductByIdWithoutChildProducts() {
        Product product = new Product();
        product.setName("Computer");
        product.setDescription("A personal computer");
        product = productRepository.save(product);
        product = productRepository.findWithChildProductsByProductId(product.getId());

        assertThat(product, notNullValue());
        assertThat(product.getChildProducts().size(), is(0));
    }

    @Test
    public void shouldFindProductByIdWithImagesAndChildProducts() {
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

        product = productRepository.findWithImagesAndChildProductsByProductId(product.getId());

        assertThat(product, notNullValue());
        assertThat(product.getImages().size(), is(1));
        assertThat(product.getChildProducts().size(), is(1));
    }

    @Test
    public void shouldFindChildProductsByProductId() {
        Product product = new Product();
        product.setName("Computer");
        product.setDescription("A personal computer");
        product = productRepository.save(product);

        Product childProduct = new Product();
        childProduct.setName("Mouse");
        childProduct.setDescription("A computer mouse");
        childProduct.setParentProduct(product);
        productRepository.save(childProduct);

        Product otherChildProducts = new Product();
        otherChildProducts.setName("Keyboard");
        otherChildProducts.setDescription("A computer keyboard");
        otherChildProducts.setParentProduct(product);
        productRepository.save(otherChildProducts);
        List<Product> childProducts = productRepository.findChildProductsByProductId(product.getId());

        assertThat(childProducts.size(), is(2));
    }
}
