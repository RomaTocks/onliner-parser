package com.onliner.tocks.service.implementation;

import com.onliner.tocks.model.filters.Filter;
import com.onliner.tocks.model.product.Fan;
import com.onliner.tocks.repository.FanRepository;
import com.onliner.tocks.service.FanService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FanServiceImpl implements FanService
{
    private final FanRepository fanRepository;

    public FanServiceImpl(FanRepository fanRepository)
    {
        this.fanRepository = fanRepository;
    }

    @Override
    public List<Fan> findAll()
    {
        return fanRepository.findAll();
    }

    @Override
    public List<Fan> findAllByPositionsNotNull()
    {
        return fanRepository.findAllByPositionsNotNull();
    }

    public List<Filter> dynamicFilters() {
        return fanRepository.filters();
    }

    @Override
    public void saveAll(List<Fan> fans)
    {
        fanRepository.saveAll(fans);
    }
}
