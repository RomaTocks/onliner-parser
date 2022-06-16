package com.onliner.tocks.service.implementation;

import com.onliner.tocks.model.filters.DynamicFilters;
import com.onliner.tocks.model.filters.Filter;
import com.onliner.tocks.repository.DynamicFiltersRepository;
import com.onliner.tocks.service.DynamicFiltersService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DynamicFiltersServiceImpl implements DynamicFiltersService
{
    private final DynamicFiltersRepository repository;

    public DynamicFiltersServiceImpl(DynamicFiltersRepository repository)
    {
        this.repository = repository;
    }

    @Override
    public DynamicFilters saveFilters(List<Filter> filters, String id)
    {
        DynamicFilters dynamicFilters = new DynamicFilters();
        dynamicFilters.setId(id);
        dynamicFilters.setFilters(filters);
        return repository.save(dynamicFilters);

    }
}
