package com.onliner.tocks.model.additional.values;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PSUAdditionalValues
{
    private Integer power;
    private Integer width;
    private Integer height;
    private Integer depth;
}
