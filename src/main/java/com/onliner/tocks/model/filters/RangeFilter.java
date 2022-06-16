package com.onliner.tocks.model.filters;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class RangeFilter<T> extends Filter
{
    private Map<String, T> values;
    private T min;
    private T max;
}
