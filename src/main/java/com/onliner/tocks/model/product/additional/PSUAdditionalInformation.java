package com.onliner.tocks.model.product.additional;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PSUAdditionalInformation extends Additional {
    String power;
    String standard;
    String efficiency;
    String certificate;
    String fan;
    String sata;
    String cpu4pin;
    String pcie6pin;
    String pcie8pin;
    String width;
    String height;
    String depth;
    String ide4pin;
    String range;
}
