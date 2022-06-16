package com.onliner.tocks.model.filters;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@ToString(callSuper = true)
public class InFilter extends Filter
{
    private List<String> values;
}
