package com.onliner.tocks.model.additional.values;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CPUAdditionalValues
{
    private Integer birthday;
    private Integer cores;
    private Integer streams;
    private Double baseFrequency;
    private Double turboFrequency;
    private Integer cpuRamChannel;
    private Integer ramFrequency;
    private Integer tdp;
}
