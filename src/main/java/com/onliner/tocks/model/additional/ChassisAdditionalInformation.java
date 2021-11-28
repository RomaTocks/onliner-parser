package com.onliner.tocks.model.additional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChassisAdditionalInformation
{
    private String psuKit;
    private String type;
    private String color;
    private String material;
    private String windowMaterial;
    private String maxSizeOfMotherboard;
    private String motherboardsCompatibleSizes;
    private String psuLocation;
    private String waterCoolingSupport;
    private String fanSection;
    private String fanKit;
    private String maxGPULength;
    private String maxCPUCoolingSystemHeight;
    private String maxPSULength;
    private String height;
    private String width;
    private String depth;
    private String weight;
}
