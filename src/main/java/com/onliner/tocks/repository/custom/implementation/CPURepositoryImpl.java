package com.onliner.tocks.repository.custom.implementation;

import com.onliner.tocks.model.filters.Filter;
import com.onliner.tocks.model.product.CPU;
import com.onliner.tocks.repository.custom.CPURepositoryCustom;
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
public class CPURepositoryImpl implements CPURepositoryCustom
{
    private final MongoTemplate mongoTemplate;

    private final Map<String, String> IN_MAP = new HashMap<>(Map.ofEntries(
            new AbstractMap.SimpleEntry<>("Комплектация", "cpuPackage"),
            new AbstractMap.SimpleEntry<>("Серия", "codename"),
            new AbstractMap.SimpleEntry<>("Встроенная графика", "cpuGraphics"),
            new AbstractMap.SimpleEntry<>("Модель", "cpuModel"),
            new AbstractMap.SimpleEntry<>("Сокет", "socket"),
            new AbstractMap.SimpleEntry<>("Охлаждение в комплекте", "stockCooling"),
            new AbstractMap.SimpleEntry<>("Память", "cpuRam"),
            new AbstractMap.SimpleEntry<>("PciExpress", "cpuPciExpress"),
            new AbstractMap.SimpleEntry<>("L2", "l2cache"),
            new AbstractMap.SimpleEntry<>("L3", "l3cache")
    ));
    private final Map<String, String> INT_RANGE_MAP = new HashMap<>(Map.ofEntries(
            new AbstractMap.SimpleEntry<>("Количество ядер", "cores"),
            new AbstractMap.SimpleEntry<>("Дата выпуска", "birthday"),
            new AbstractMap.SimpleEntry<>("Количество потоков", "streams"),
            new AbstractMap.SimpleEntry<>("Частота памяти", "ramFrequency"),
            new AbstractMap.SimpleEntry<>("TDP", "tdp"),
            new AbstractMap.SimpleEntry<>("Каналы памяти", "cpuRamChannel")
    ));
    private final Map<String, String> DOUBLE_RANGE_MAP = new HashMap<>(Map.ofEntries(
            new AbstractMap.SimpleEntry<>("Базовая частота", "baseFrequency"),
            new AbstractMap.SimpleEntry<>("Turbo-частота", "turboFrequency")
    ));

    @Autowired
    public CPURepositoryImpl(MongoTemplate mongoTemplate)
    {
        this.mongoTemplate = mongoTemplate;
    }

    public List<Filter> filters() {
        Query query = new Query();
        List<CPU> cpus = mongoTemplate.find(query, CPU.class);
        return getFilters(cpus, IN_MAP, INT_RANGE_MAP, DOUBLE_RANGE_MAP);
    }
}