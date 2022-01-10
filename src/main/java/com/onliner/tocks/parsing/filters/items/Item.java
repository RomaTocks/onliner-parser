package com.onliner.tocks.parsing.filters.items;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Item
{
    private String id;
    private String name;
}
