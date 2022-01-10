package com.onliner.tocks.parsing.filters.items;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Placeholder
{
    private Double from;
    private Double to;
}
