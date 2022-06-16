package com.onliner.tocks.model.product.additional.values;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChassisAdditionalValues extends Values
{
    Integer fanKit;
    Integer length;
    Integer height;
    Double weight;
    Integer depth;
    Integer maxGPULength;
    Integer maxCPUCoolingSystemHeight;
    Integer maxPSULength;
}
