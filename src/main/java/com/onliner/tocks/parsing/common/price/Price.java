package com.onliner.tocks.parsing.common.price;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Price {
    private String amount;
    private Double value;
    private String currency;

}
