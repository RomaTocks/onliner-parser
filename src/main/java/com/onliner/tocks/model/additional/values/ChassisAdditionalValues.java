package com.onliner.tocks.model.additional.values;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChassisAdditionalValues
{
    private Integer fanKit;
    private Double length;
    private Double height;
    private Double weight;
    private Double depth;
    private Double maxGPULength;
    private Double maxCPUCoolingSystemHeight;
    private Double maxPSULength;
}
