package com.onliner.tocks.service;

import com.onliner.tocks.model.filters.Filter;
import com.onliner.tocks.model.product.Ram;

import java.util.List;

public interface RamService {
    List<Ram> findAll();
    List<Ram> findAllByPositionsNotNull();
    void saveAll(List<Ram> rams);
    List<Filter> dynamicFilters();
}
