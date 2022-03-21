package com.onliner.tocks.model.additional;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HDDAdditionalInformation {
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
    // TODO: 20.11.2021 Дописать класс.

}
