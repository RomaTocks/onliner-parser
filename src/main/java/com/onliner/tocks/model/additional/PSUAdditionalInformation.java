package com.onliner.tocks.model.additional;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PSUAdditionalInformation {
    private String power;
    private String standard;
    private String efficiency;
    private String certificate;
    private String fan;
    private String sata;
    private String cpu4pin;
    private String pcie6pin;
    private String pcie8pin;
    private String width;
    private String height;
    private String depth;
    private String ide4pin;
    private String range;
    // TODO: 20.11.2021 Дописать класс.
}
