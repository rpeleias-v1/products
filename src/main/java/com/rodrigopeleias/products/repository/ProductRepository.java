package com.rodrigopeleias.products.repository;

import com.rodrigopeleias.products.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p left join fetch p.images im")
    List<Product> findAllWithImages();

    @Query("select p from Product p left join fetch p.childProducts cp")
    List<Product> findAllWithChildProducts();

    @Query("select p from Product p left join fetch p.childProducts cp left join fetch p.images im")
    List<Product> findAllWithImagesAndChildProducts();

    @Query("select p from Product p left join fetch p.images im where p.id = ?1")
    Product findWithImagesByProductId(Long productId);

    @Query("select p from Product p left join fetch p.childProducts cp where p.id = ?1")
    Product findWithChildProductsByProductId(Long productId);

    @Query("select p from Product p left join fetch p.childProducts cp left join fetch p.images im where p.id = ?1")
    Product findWithImagesAndChildProductsByProductId(Long productId);

    @Query("select p.childProducts from Product p where p.id = ?1")
    List<Product> findChildProductsByProductId(Long productId);
}
