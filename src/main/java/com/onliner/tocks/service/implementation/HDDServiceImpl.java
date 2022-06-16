package com.onliner.tocks.service.implementation;

import com.onliner.tocks.model.filters.Filter;
import com.onliner.tocks.model.product.HDD;
import com.onliner.tocks.repository.HDDRepository;
import com.onliner.tocks.response.exception.RequestException;
import com.onliner.tocks.service.HDDService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.onliner.tocks.response.Response.configureResponse;

@Service
public class HDDServiceImpl implements HDDService
{
    private final HDDRepository hddRepository;

    public HDDServiceImpl(HDDRepository hddRepository)
    {
        this.hddRepository = hddRepository;
    }

    @Override
    public List<HDD> findAll()
    {
        return hddRepository.findAll();
    }

    @Override
    public List<HDD> findAllByPositionsNotNull()
    {
        return hddRepository.findAllByPositionsNotNull();
    }

    @Override
    public void saveAll(List<HDD> hdds)
    {
        hddRepository.saveAll(hdds);
    }
}
