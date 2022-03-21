package com.onliner.tocks.model.additional.values;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PSUAdditionalValues
{
    private Integer power;
    private Double width;
    private Double height;
    private Double depth;
}
