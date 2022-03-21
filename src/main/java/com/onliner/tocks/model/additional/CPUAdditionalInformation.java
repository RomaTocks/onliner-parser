package com.onliner.tocks.model.additional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CPUAdditionalInformation {
    private String birthday;
    private String cpuModel;
    private String socket;
    private String cores;
    private String streams;
    private String baseFrequency;
    private String turboFrequency;
    private String cpuRam;
    private String cpuRamChannel;
    private String ramFrequency;
    private String codename;
    private String cpuPackage;
    private String cpuPciExpress;
    private String tdp;
    private String l2cache;
    private String l3cache;
    private String stockCooling;
    private String cpuGraphics;
    private String techProcess;
    private String multiThread;
    // TODO: 20.11.2021 Дописать класс.
}
