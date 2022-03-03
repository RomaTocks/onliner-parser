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
    private Integer length;
    private Integer height;
    private Double weight;
    private Integer depth;
    private Integer maxGPULength;
    private Integer maxCPUCoolingSystemHeight;
    private Integer maxPSULength;
}
