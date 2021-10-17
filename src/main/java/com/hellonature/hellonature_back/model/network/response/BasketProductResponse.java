package com.hellonature.hellonature_back.model.network.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasketProductResponse {
    private Long idx;
    private String name;
    private Integer netPrice;
    private Integer salePrice;
    private Integer price;
    private String img;
    private Integer count;
}
