package com.onliner.tocks.service;

import com.onliner.tocks.model.filters.Filter;
import com.onliner.tocks.model.product.Chassis;

import java.util.List;

public interface ChassisService
{
    List<Chassis> findAll();
    List<Chassis> findAllByPositionsNotNull();
    void saveAll(List<Chassis> cpus);
    List<Filter> dynamicFilters();
}
