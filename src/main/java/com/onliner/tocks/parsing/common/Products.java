package com.onliner.tocks.parsing.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Products {
    private Page page;
    private List<Product> products;
    private Integer total;

    public void addAllNewProducts(List<Product> newProducts) {
        products.addAll(newProducts);
    }
}
