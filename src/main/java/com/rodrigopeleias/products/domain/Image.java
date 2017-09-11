package com.rodrigopeleias.products.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Image {

    @Id
    private Long id;

    private String type;


}
