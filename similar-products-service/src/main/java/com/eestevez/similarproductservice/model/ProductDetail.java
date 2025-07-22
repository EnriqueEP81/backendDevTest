package com.eestevez.similarproductservice.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductDetail {
    private String id;
    private String name;
    private String price;
    private Boolean availability;
}
