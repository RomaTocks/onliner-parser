package com.onliner.tocks.service.implementation;

import com.onliner.tocks.model.filters.Filter;
import com.onliner.tocks.model.product.PSU;
import com.onliner.tocks.repository.PSURepository;
import com.onliner.tocks.response.exception.RequestException;
import com.onliner.tocks.service.PSUService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.onliner.tocks.response.Response.configureResponse;

@Service
public class PSUServiceImpl implements PSUService
{
    private final PSURepository psuRepository;

    public PSUServiceImpl(PSURepository psuRepository)
    {
        this.psuRepository = psuRepository;
    }

    @Override
    public List<PSU> findAll()
    {
        return psuRepository.findAll();
    }

    @Override
    public List<PSU> findAllByPositionsNotNull()
    {
        return psuRepository.findAllByPositionsNotNull();
    }

    public List<Filter> dynamicFilters() {
        return psuRepository.filters();
    }

    @Override
    public void saveAll(List<PSU> psus)
    {
        psuRepository.saveAll(psus);
    }
}
