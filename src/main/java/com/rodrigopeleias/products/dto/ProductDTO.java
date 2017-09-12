package com.rodrigopeleias.products.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

public class ProductDTO implements Serializable{

    private Long id;
    private String name;
    private String description;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private ProductDTO parentProduct;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<ImageDTO> images;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductDTO getParentProduct() {
        return parentProduct;
    }

    public void setParentProduct(ProductDTO parentProduct) {
        this.parentProduct = parentProduct;
    }

    public List<ImageDTO> getImages() {
        return images;
    }

    public void setImages(List<ImageDTO> images) {
        this.images = images;
    }
}
