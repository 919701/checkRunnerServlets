package ru.clevertec.checkrunnerservlets.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private Long id;
    private String name;
    private Double price;
    private Boolean discount = false;

    public Product(String name, Double price, Boolean discount) {
        this.name = name;
        this.price = price;
        this.discount = discount;
    }


}
