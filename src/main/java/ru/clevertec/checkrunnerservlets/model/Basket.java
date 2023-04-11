package ru.clevertec.checkrunnerservlets.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Basket {

    private Long id;
    private String nameProduct;
    private Double priceProduct;
    private Double quantityProduct;
    private Boolean discountProduct;
    private Double totalPrice;
    private Double discountCardPercent;
    private Double totalDiscount;

    public Basket(String nameProduct, Double priceProduct, Double quantityProduct, Boolean discountProduct, Double totalPrice, Double discountCardPercent, Double totalDiscount) {
        this.nameProduct = nameProduct;
        this.priceProduct = priceProduct;
        this.quantityProduct = quantityProduct;
        this.discountProduct = discountProduct;
        this.totalPrice = totalPrice;
        this.discountCardPercent = discountCardPercent;
        this.totalDiscount = totalDiscount;
    }
}
