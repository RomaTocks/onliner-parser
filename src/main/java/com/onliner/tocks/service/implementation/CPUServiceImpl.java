package com.onliner.tocks.service.implementation;

import com.onliner.tocks.model.filters.Filter;
import com.onliner.tocks.model.product.CPU;
import com.onliner.tocks.repository.CPURepository;
import com.onliner.tocks.service.CpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CPUServiceImpl implements CpuService
{
    private final CPURepository repository;
    @Autowired
    public CPUServiceImpl(CPURepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CPU> findAll() {
        return repository.findAll();
    }

    @Override
    public List<CPU> findAllByPositionsNotNull()
    {
        return repository.findAllByPositionsNotNull();
    }

    public List<Filter> dynamicFilters() {
        return repository.filters();
    }

    @Override
    public void saveAll(List<CPU> cpus) {
        repository.saveAll(cpus);
    }
}
