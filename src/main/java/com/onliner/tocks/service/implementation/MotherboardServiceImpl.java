package com.onliner.tocks.service.implementation;

import com.onliner.tocks.model.filters.Filter;
import com.onliner.tocks.model.product.Motherboard;
import com.onliner.tocks.repository.MotherboardRepository;
import com.onliner.tocks.response.exception.RequestException;
import com.onliner.tocks.service.MotherboardService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.onliner.tocks.response.Response.configureResponse;

@Service
public class MotherboardServiceImpl implements MotherboardService
{
    private final MotherboardRepository motherboardRepository;

    public MotherboardServiceImpl(MotherboardRepository motherboardRepository)
    {
        this.motherboardRepository = motherboardRepository;
    }

    @Override
    public List<Motherboard> findAll()
    {
        return motherboardRepository.findAll();
    }

    @Override
    public List<Motherboard> findAllByPositionsNotNull()
    {
        return motherboardRepository.findAllByPositionsNotNull();
    }

    public List<Filter> dynamicFilters() {
        return motherboardRepository.filters();
    }

    @Override
    public void saveAll(List<Motherboard> motherboards)
    {
        motherboardRepository.saveAll(motherboards);
    }
}
