package com.onliner.tocks.model.filters;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Filter
{
    private String name;
    private String parameterName;
    private String type;
}
