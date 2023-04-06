package ru.clevertec.checkrunnerservlets.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Basket {

    private Map<Product, Double> products = new HashMap<>();
    private DiscountCard discountCard;

}
