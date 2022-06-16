package com.onliner.tocks.model.product.additional.values;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GraphicCardAdditionalValues extends Values
{
    Integer baseFrequency;
    Integer turboFrequency;
    Integer streamCores;
    Integer videoMemory;
    Integer memoryFrequency;
    Integer memoryBandwidth;
    Integer memoryBusWidth;
    Integer PSU;
    Integer tdp;
    Double length;
    Double height;
    Integer fanCount;
}
