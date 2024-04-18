package com.quetion.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Setter
@Getter
public class UserOrderDetails {
    private List<Integer> allProductPrices;
    private List<Integer> payablePrice;
    private List<Integer> discountPrice;

}
