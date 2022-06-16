package com.onliner.tocks.service;

import com.onliner.tocks.model.filters.Filter;
import com.onliner.tocks.model.product.Motherboard;

import java.util.List;

public interface MotherboardService {
    List<Motherboard> findAll();
    List<Motherboard> findAllByPositionsNotNull();
    void saveAll(List<Motherboard> motherboards);
    List<Filter> dynamicFilters();
}
