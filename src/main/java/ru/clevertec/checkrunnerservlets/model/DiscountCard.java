package ru.clevertec.checkrunnerservlets.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class DiscountCard {

    private Long id;
    private Integer numberCard;
    private Double discountPercent;


}
