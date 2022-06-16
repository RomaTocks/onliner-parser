package com.onliner.tocks.model.product.additional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CPUAdditionalInformation extends Additional
{
    String birthday;
    String cpuModel;
    String socket;
    String cores;
    String streams;
    String baseFrequency;
    String turboFrequency;
    String cpuRam;
    String cpuRamChannel;
    String ramFrequency;
    String codename;
    String cpuPackage;
    String cpuPciExpress;
    String tdp;
    String l2cache;
    String l3cache;
    String stockCooling;
    String cpuGraphics;
    String techProcess;
    String multiThread;
}
