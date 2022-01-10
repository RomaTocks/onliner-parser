package com.onliner.tocks.parsing.filters;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Facet
{
    private ProductSpecifications general;
    private ProductSpecifications additional;
}
