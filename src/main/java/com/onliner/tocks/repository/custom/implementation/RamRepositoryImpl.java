package com.onliner.tocks.repository.custom.implementation;

import com.onliner.tocks.model.filters.Filter;
import com.onliner.tocks.model.product.Ram;
import com.onliner.tocks.repository.custom.RamRepositoryCustom;
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
public class RamRepositoryImpl implements RamRepositoryCustom
{
    private final MongoTemplate mongoTemplate;

    private final Map<String, String> IN_MAP = new HashMap<>(Map.ofEntries(
            new AbstractMap.SimpleEntry<>("Тип", "type"),
            new AbstractMap.SimpleEntry<>("Тайминги", "timing"),
            new AbstractMap.SimpleEntry<>("XMP-профиль", "xmp")
    ));
    private final Map<String, String> INT_RANGE_MAP = new HashMap<>(Map.ofEntries(
            new AbstractMap.SimpleEntry<>("Набор", "kit"),
            new AbstractMap.SimpleEntry<>("Рабочая частота", "frequency"),
            new AbstractMap.SimpleEntry<>("Общий объем", "value"),
            new AbstractMap.SimpleEntry<>("Объем одного модуля", "singleValue")
    ));
    private final Map<String, String> DOUBLE_RANGE_MAP = new HashMap<>(Map.ofEntries(
    ));

    @Autowired
    public RamRepositoryImpl(MongoTemplate mongoTemplate)
    {
        this.mongoTemplate = mongoTemplate;
    }

    public List<Filter> filters()
    {
        Query query = new Query();
        List<Ram> ram = mongoTemplate.find(query, Ram.class);
        return getFilters(ram, IN_MAP, INT_RANGE_MAP, DOUBLE_RANGE_MAP);
    }
}
