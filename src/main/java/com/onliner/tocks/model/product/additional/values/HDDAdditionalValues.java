package com.onliner.tocks.model.product.additional.values;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HDDAdditionalValues extends Values
{
    private Integer depth;
    private Integer volume;
    private Double powerUsage;
}
