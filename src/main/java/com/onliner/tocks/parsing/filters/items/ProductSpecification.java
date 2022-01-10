package com.onliner.tocks.parsing.filters.items;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ProductSpecification
{
    private String type;
    private String parameter_id;
    private String dictionary_id;
    private String name;
    private String description;
}
