package com.onliner.tocks.parsing.common.price;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Offer {
    private Integer count;
}
