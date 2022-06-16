package com.onliner.tocks.service;

import com.onliner.tocks.model.filters.Filter;
import com.onliner.tocks.model.product.Fan;

import java.util.List;

public interface FanService {
    List<Fan> findAll();
    List<Fan> findAllByPositionsNotNull();
    void saveAll(List<Fan> fans);
    List<Filter> dynamicFilters();
}
