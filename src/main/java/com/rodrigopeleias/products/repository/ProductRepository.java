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

    @Query("select p from Product p left join fetch p.parentProduct pp")
    List<Product> findAllWithParentProduct();

    @Query("select p from Product p left join fetch p.parentProduct pp left join fetch p.images im")
    List<Product> findAllWithImagesAndParentProduct();

    @Query("select p from Product p left join fetch p.images im where p.id = ?1")
    Product findWithImagesByProductId(Long productId);

    @Query("select p from Product p left join fetch p.parentProduct pp where p.id = ?1")
    Product findWithParentProductByProductId(Long productId);

    @Query("select p from Product p left join fetch p.parentProduct pp left join fetch p.images im where p.id = ?1")
    Product findWithImagesAndParentProductByProductId(Long productId);

    @Query("select p.parentProduct from Product p where p.id = ?1")
    Product findParentProductsByProductId(Long productId);
}
