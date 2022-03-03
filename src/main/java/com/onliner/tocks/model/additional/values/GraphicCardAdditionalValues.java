package com.onliner.tocks.model.additional.values;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GraphicCardAdditionalValues
{
    private Integer baseFrequency;
    private Integer turboFrequency;
    private Integer streamCores;
    private Integer videoMemory;
    private Integer memoryFrequency;
    private Integer memoryBandwidth;
    private Integer memoryBusWidth;
    private Integer PSU;
    private Integer length;
    private Integer height;
    private Integer fanCount;
}
