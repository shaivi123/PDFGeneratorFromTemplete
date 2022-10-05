package com.springrest.ThymeleafDemo.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductReq {

    private String productName;
    private String description;
    private String price;
    private String quantity;

}
