package com.onliner.tocks.service.implementation;

import com.onliner.tocks.model.filters.Filter;
import com.onliner.tocks.model.product.GraphicCard;
import com.onliner.tocks.repository.GraphicCardRepository;
import com.onliner.tocks.service.GraphicCardService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GraphicCardImpl implements GraphicCardService
{
    private final GraphicCardRepository graphicCardRepository;

    public GraphicCardImpl(GraphicCardRepository graphicCardRepository)
    {
        this.graphicCardRepository = graphicCardRepository;
    }

    @Override
    public List<GraphicCard> findAll()
    {
        return graphicCardRepository.findAll();
    }

    @Override
    public List<GraphicCard> findAllByPositionsNotNull()
    {
        return graphicCardRepository.findAllByPositionsNotNull();
    }

    public List<Filter> dynamicFilters() {
        return graphicCardRepository.filters();
    }

    @Override
    public void saveAll(List<GraphicCard> graphicCards)
    {
        graphicCardRepository.saveAll(graphicCards);
    }
}
