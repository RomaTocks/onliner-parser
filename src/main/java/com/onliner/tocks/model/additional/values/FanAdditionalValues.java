package com.onliner.tocks.model.additional.values;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FanAdditionalValues
{
    private Integer width;
    private Integer height;
    private Double weight;
    private Integer depth;
    private Integer diameterFan;
    private Integer dispelPower;
}
