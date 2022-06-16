package com.onliner.tocks.repository.custom.implementation;

import com.onliner.tocks.model.filters.Filter;
import com.onliner.tocks.model.product.Chassis;
import com.onliner.tocks.repository.custom.ChassisRepositoryCustom;
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
public class ChassisRepositoryImpl implements ChassisRepositoryCustom
{
    private final MongoTemplate mongoTemplate;

    private final Map<String, String> IN_MAP = new HashMap<>(Map.ofEntries(
            new AbstractMap.SimpleEntry<>("БП", "psuKit"),
            new AbstractMap.SimpleEntry<>("Цвет", "color"),
            new AbstractMap.SimpleEntry<>("Макс. размер материнской платы", "maxSizeOfMotherboard"),
            new AbstractMap.SimpleEntry<>("Расположение блока питания", "psuLocation"),
            new AbstractMap.SimpleEntry<>("Водяное охлаждение", "waterCoolingSupport")
    ));
    private final Map<String, String> INT_RANGE_MAP = new HashMap<>(Map.ofEntries(
            new AbstractMap.SimpleEntry<>("Вентиляторов в комплекте", "fanKit"),
            new AbstractMap.SimpleEntry<>("Высота", "height"),
            new AbstractMap.SimpleEntry<>("Макс. длинна видеокарты", "maxGPULength"),
            new AbstractMap.SimpleEntry<>("Макс. высота кулера", "maxCPUCoolingSystemHeight")
    ));
    private final Map<String, String> DOUBLE_RANGE_MAP = new HashMap<>(Map.ofEntries(
            new AbstractMap.SimpleEntry<>("Вес", "weight")
    ));

    @Autowired
    public ChassisRepositoryImpl(MongoTemplate mongoTemplate)
    {
        this.mongoTemplate = mongoTemplate;
    }

    public List<Filter> filters() {
        Query query = new Query();
        List<Chassis> chassises = mongoTemplate.find(query, Chassis.class);
        return getFilters(chassises, IN_MAP, INT_RANGE_MAP, DOUBLE_RANGE_MAP);
    }
}
