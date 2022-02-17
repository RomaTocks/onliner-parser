package com.onliner.tocks.parsing.common.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Manufacturer
{
    private String key;
    private String name;
}
