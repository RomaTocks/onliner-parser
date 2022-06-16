package com.onliner.tocks.model.product.additional;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HDDAdditionalInformation extends Additional {
    private String volume;
    private String formFactor;
    private String buffer;
    private String spindleSpeed;
    private String readSpeed;
    private String writeSpeed;
    private String hddInterface;
    private String encryption;
    private String sectorSize;
    private String readOrWriteNoise;
    private String waitingNoise;
    private String powerUsage;
    private String depth;

}
