package com.onliner.tocks.model.product.additional;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SSDAdditionalInformation extends Additional {
    private String birthday;
    private String volume;
    private String formFactor;
    private String ssdInterface;
    private String microType;
    private String depth;
    private String cooling;
    private String adapter;
    private String readSpeed;
    private String writeSpeed;
    private String workTime;
    private String controller;
    private String m2Size;
    private String recordResource;
    private String encryption;
    private String backlight;

}
