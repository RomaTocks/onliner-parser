package com.onliner.tocks.repository.custom.implementation;

import com.onliner.tocks.model.filters.Filter;
import com.onliner.tocks.model.product.GraphicCard;
import com.onliner.tocks.repository.custom.GraphicCardRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.onliner.tocks.repository.criteria.FilterCriteria.getFilters;


@Component
public class GraphicCardRepositoryImpl implements GraphicCardRepositoryCustom
{
    private final MongoTemplate mongoTemplate;

    private final String[] IN_VALUES = {"manufacturer", "gpuInterface", "gpu", "lhr", "memoryType", "directx", "sliCrossFire"};
    private final String[] INT_RANGE = {"baseFrequency", "turboFrequency", "streamCores", "videoMemory", "memoryFrequency", "memoryBandwidth", "memoryBusWidth", "tdp", "fanCount"};
    private final String[] DOUBLE_RANGE = {"length","height"};
    private final Map<String, String> IN_MAP = new HashMap<>(Map.ofEntries(
            new AbstractMap.SimpleEntry<>("Производитель", "manufacturer"),
            new AbstractMap.SimpleEntry<>("PCI", "gpuInterface"),
            new AbstractMap.SimpleEntry<>("Графический чип", "gpu"),
            new AbstractMap.SimpleEntry<>("Защита от майнинга", "lhr"),
            new AbstractMap.SimpleEntry<>("Память", "memoryType"),
            new AbstractMap.SimpleEntry<>("DirectX", "directx"),
            new AbstractMap.SimpleEntry<>("SLI/CrossFire", "sliCrossFire")
    ));
    private final Map<String, String> INT_RANGE_MAP = new HashMap<>(Map.ofEntries(
           // new AbstractMap.SimpleEntry<>("Базовая частота", "baseFrequency"),
           // new AbstractMap.SimpleEntry<>("Turbo частота", "turboFrequency"),
           // new AbstractMap.SimpleEntry<>("Количество потоковых ядер", "streamCores"),
           // new AbstractMap.SimpleEntry<>("Видеопамять", "ramFrequency"),
            new AbstractMap.SimpleEntry<>("TDP", "tdp")
           // new AbstractMap.SimpleEntry<>("Частота памяти", "memoryFrequency")
    ));
    private final Map<String, String> DOUBLE_RANGE_MAP = new HashMap<>(Map.ofEntries(
            new AbstractMap.SimpleEntry<>("Длинна", "length"),
            new AbstractMap.SimpleEntry<>("Высота", "height")
    ));

    @Autowired
    public GraphicCardRepositoryImpl(MongoTemplate mongoTemplate)
    {
        this.mongoTemplate = mongoTemplate;
    }

    public List<Filter> filters()
    {
        Query query = new Query();
        List<GraphicCard> graphicCards = mongoTemplate.find(query, GraphicCard.class);
        return getFilters(graphicCards, IN_MAP, INT_RANGE_MAP, DOUBLE_RANGE_MAP);
    }
}
