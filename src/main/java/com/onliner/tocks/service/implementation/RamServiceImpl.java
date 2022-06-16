package com.onliner.tocks.service.implementation;

import com.onliner.tocks.model.filters.Filter;
import com.onliner.tocks.model.product.Ram;
import com.onliner.tocks.repository.RamRepository;
import com.onliner.tocks.response.exception.RequestException;
import com.onliner.tocks.service.RamService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.onliner.tocks.response.Response.configureResponse;

@Service
public class RamServiceImpl implements RamService
{
    private final RamRepository ramRepository;

    public RamServiceImpl(RamRepository ramRepository)
    {
        this.ramRepository = ramRepository;
    }

    @Override
    public List<Ram> findAll()
    {
        return ramRepository.findAll();
    }

    @Override
    public List<Ram> findAllByPositionsNotNull()
    {
        return ramRepository.findAllByPositionsNotNull();
    }

    public List<Filter> dynamicFilters() {
        return ramRepository.filters();
    }

    @Override
    public void saveAll(List<Ram> rams)
    {
        ramRepository.saveAll(rams);
    }
}
