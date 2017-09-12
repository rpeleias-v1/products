package com.rodrigopeleias.products.repository;

import com.rodrigopeleias.products.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p join fetch p.images im")
    List<Product> findAllWithImages();

    @Query("select p from Product p join fetch p.parentProduct p")
    List<Product> findAllWithParentProduct();

    @Query("select p from Product p join fetch p.parentProduct p join fetch p.images im")
    List<Product> findAllWithImagesAndParentProduct();

}
