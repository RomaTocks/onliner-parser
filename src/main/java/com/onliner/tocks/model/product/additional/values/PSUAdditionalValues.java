package com.onliner.tocks.model.product.additional.values;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PSUAdditionalValues extends Values
{
    Integer power;
    Integer width;
    Integer height;
    Integer depth;
}
