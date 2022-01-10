package com.onliner.tocks.parsing.filters;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.onliner.tocks.parsing.filters.items.ProductSpecification;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ProductSpecifications
{
    private List<ProductSpecification> items;
}
