package com.onliner.tocks.repository.custom.implementation;

import com.onliner.tocks.model.filters.Filter;
import com.onliner.tocks.model.product.Motherboard;
import com.onliner.tocks.repository.custom.MotherboardRepositoryCustom;
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
public class MotherboardRepositoryImpl implements MotherboardRepositoryCustom
{
    private final MongoTemplate mongoTemplate;

    private final Map<String, String> IN_MAP = new HashMap<>(Map.ofEntries(
            new AbstractMap.SimpleEntry<>("Поддержка процессоров", "cpuSupport"),
            new AbstractMap.SimpleEntry<>("Сокет", "socket"),
            new AbstractMap.SimpleEntry<>("Форм-фактор", "formFactor"),
            new AbstractMap.SimpleEntry<>("Режим памяти", "memoryMode"),
            new AbstractMap.SimpleEntry<>("Wi-Fi", "wifi"),
            new AbstractMap.SimpleEntry<>("Чипсет", "chipset"),
            new AbstractMap.SimpleEntry<>("Подсветка", "backlight"),
            new AbstractMap.SimpleEntry<>("Поддержка встроенной графики", "integratedGraphics"),
            new AbstractMap.SimpleEntry<>("Поддержка SLI", "sli")
    ));
    private final Map<String, String> INT_RANGE_MAP = new HashMap<>(Map.ofEntries(
    ));
    private final Map<String, String> DOUBLE_RANGE_MAP = new HashMap<>(Map.ofEntries(
    ));

    @Autowired
    public MotherboardRepositoryImpl(MongoTemplate mongoTemplate)
    {
        this.mongoTemplate = mongoTemplate;
    }

    public List<Filter> filters() {
        Query query = new Query();
        List<Motherboard> motherboards = mongoTemplate.find(query, Motherboard.class);
        return getFilters(motherboards, IN_MAP, INT_RANGE_MAP, DOUBLE_RANGE_MAP);
    }
}
