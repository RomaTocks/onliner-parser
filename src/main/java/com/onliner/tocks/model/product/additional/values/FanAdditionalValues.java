package com.onliner.tocks.model.product.additional.values;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FanAdditionalValues extends Values
{
    Integer width;
    Integer height;
    Double weight;
    Integer depth;
    Integer diameterFan;
    Integer dispelPower;
}
