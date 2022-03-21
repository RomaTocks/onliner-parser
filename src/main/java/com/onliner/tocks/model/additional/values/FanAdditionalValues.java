package com.onliner.tocks.model.additional.values;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FanAdditionalValues
{
    private Double width;
    private Double height;
    private Double weight;
    private Integer depth;
    private Double diameterFan;
    private Integer dispelPower;
}
