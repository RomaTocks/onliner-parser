package com.onliner.tocks.service.implementation;

import com.onliner.tocks.model.filters.Filter;
import com.onliner.tocks.model.product.SSD;
import com.onliner.tocks.repository.SSDRepository;
import com.onliner.tocks.response.exception.RequestException;
import com.onliner.tocks.service.SSDService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.onliner.tocks.response.Response.configureResponse;

@Service
public class SSDServiceImpl implements SSDService
{
    private final SSDRepository ssdRepository;

    public SSDServiceImpl(SSDRepository ssdRepository)
    {
        this.ssdRepository = ssdRepository;
    }

    @Override
    public List<SSD> findAll()
    {
        return ssdRepository.findAll();
    }

    @Override
    public List<SSD> findAllByPositionsNotNull()
    {
        return ssdRepository.findAllByPositionsNotNull();
    }

    @Override
    public void saveAll(List<SSD> ssds)
    {
        ssdRepository.saveAll(ssds);
    }
}
