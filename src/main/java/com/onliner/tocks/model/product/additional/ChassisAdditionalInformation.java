package com.onliner.tocks.model.product.additional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChassisAdditionalInformation extends Additional
{
    String psuKit;
    String type;
    String color;
    String material;
    String windowMaterial;
    String maxSizeOfMotherboard;
    String motherboardsCompatibleSizes;
    String psuLocation;
    String waterCoolingSupport;
    String fanSection;
    String fanKit;
    String maxGPULength;
    String maxCPUCoolingSystemHeight;
    String maxPSULength;
    String height;
    String width;
    String depth;
    String weight;
}
