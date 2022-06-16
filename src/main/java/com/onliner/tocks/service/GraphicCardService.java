package com.onliner.tocks.service;

import com.onliner.tocks.model.filters.Filter;
import com.onliner.tocks.model.product.GraphicCard;

import java.util.List;

public interface GraphicCardService {
    List<GraphicCard> findAll();
    List<GraphicCard> findAllByPositionsNotNull();
    void saveAll(List<GraphicCard> graphicCards);
    List<Filter> dynamicFilters();
}
