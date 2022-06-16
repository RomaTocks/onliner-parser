package com.onliner.tocks.service;

import com.onliner.tocks.model.filters.Filter;
import com.onliner.tocks.model.product.CPU;

import java.util.List;

public interface CpuService
{
    List<CPU> findAll();
    List<CPU> findAllByPositionsNotNull();
    void saveAll(List<CPU> cpus);
    List<Filter> dynamicFilters();
}
