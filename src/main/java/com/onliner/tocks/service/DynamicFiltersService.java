package com.onliner.tocks.service;

import com.onliner.tocks.model.filters.DynamicFilters;
import com.onliner.tocks.model.filters.Filter;

import java.util.List;

public interface DynamicFiltersService
{
    DynamicFilters saveFilters(List<Filter> filters, String id);
}
