package com.onliner.tocks.service.implementation;

import com.onliner.tocks.model.filters.Filter;
import com.onliner.tocks.model.product.Chassis;
import com.onliner.tocks.repository.ChassisRepository;
import com.onliner.tocks.service.ChassisService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChassisServiceImpl implements ChassisService
{
    private final ChassisRepository chassisRepository;

    public ChassisServiceImpl(ChassisRepository chassisRepository)
    {
        this.chassisRepository = chassisRepository;
    }

    @Override
    public List<Chassis> findAll()
    {
        return chassisRepository.findAll();
    }

    @Override
    public List<Chassis> findAllByPositionsNotNull()
    {
        return chassisRepository.findAllByPositionsNotNull();
    }

    @Override
    public List<Filter> dynamicFilters() {
        return chassisRepository.filters();
    }

    @Override
    public void saveAll(List<Chassis> chassis)
    {
        chassisRepository.saveAll(chassis);
    }
}
