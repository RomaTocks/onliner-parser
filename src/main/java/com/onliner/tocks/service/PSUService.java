package com.onliner.tocks.service;

import com.onliner.tocks.model.filters.Filter;
import com.onliner.tocks.model.product.PSU;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface PSUService {
    List<PSU> findAll();
    List<PSU> findAllByPositionsNotNull();
    void saveAll(List<PSU> psus);
    List<Filter> dynamicFilters();
}
