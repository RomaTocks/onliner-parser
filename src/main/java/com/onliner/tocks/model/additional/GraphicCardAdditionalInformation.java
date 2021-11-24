package com.onliner.tocks.model.additional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class GraphicCardAdditionalInformation
{
    private String birthday;
    private String gpuInterface;
    private String manufacturer;
    private String gpu;
    private String boost;
    private String lhr;
    private String baseFrequency;
    private String turboFrequency;
    private String streamCores;
    private String videoMemory;
    private String memoryType;
    private String memoryFrequency;
    private String memoryBandwidth;
    private String memoryBusWidth;
    private String directx;
    private String sliCrossFire;
    private String powerConnectors;
    private String PSU;
    private String length;
    private String hdmi;
    private String miniHdmi;
    private String vga;
    private String displayPort;
    // TODO: 20.11.2021 Дописать класс.
}
