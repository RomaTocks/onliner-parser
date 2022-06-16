package com.onliner.tocks.model.product.additional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GraphicCardAdditionalInformation extends Additional
{
    String birthday;
    String gpuInterface;
    String manufacturer;
    String gpu;
    String boost;
    String lhr;
    String tdp;
    String baseFrequency;
    String turboFrequency;
    String streamCores;
    String videoMemory;
    String memoryType;
    String memoryFrequency;
    String memoryBandwidth;
    String memoryBusWidth;
    String directx;
    String sliCrossFire;
    String powerConnectors;
    String PSU;
    String length;
    String hdmi;
    String miniHdmi;
    String vga;
    String displayPort;
    String height;
    String cooling;
    String fanCount;
}
