package com.onliner.tocks.model.product.additional;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RamAdditionalInformation extends Additional {
    String kit;
    String value;
    String singleValue;
    String type;
    String frequency;
    String timing;
    String xmp;
    String color;
}
