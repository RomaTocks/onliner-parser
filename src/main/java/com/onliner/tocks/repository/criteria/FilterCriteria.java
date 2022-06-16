package com.onliner.tocks.repository.criteria;


import com.onliner.tocks.model.filters.Filter;
import com.onliner.tocks.model.filters.InFilter;
import com.onliner.tocks.model.filters.RangeFilter;
import com.onliner.tocks.model.product.*;
import com.onliner.tocks.model.product.additional.Additional;
import com.onliner.tocks.model.product.additional.values.Values;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class FilterCriteria
{
    public static<T extends Product> List<Filter> getFilters(List<T> products, Map<String, String> in, Map<String, String> intRange, Map<String, String> doubleRange) {
        List<Filter> filters = new ArrayList<>();
        in.forEach((key, value) -> {
            InFilter inFilter = new InFilter();;
            inFilter.setName(key);
            inFilter.setParameterName(value);
            inFilter.setType("in");
            inFilter.setValues(inFilterValues(products, value));
            filters.add(inFilter);
        });
        intRange.forEach((key, value) -> {
            RangeFilter<Integer> intFilter = new RangeFilter<>();
            intFilter.setName(key);
            intFilter.setParameterName(value);
            intFilter.setType("range");
            OptionalInt minValue = productIntStream(products, value).min();
            if(minValue.isPresent()) {
                intFilter.setMin(minValue.getAsInt());
            }
            OptionalInt maxValue = productIntStream(products, value).max();
            if(maxValue.isPresent()) {
                intFilter.setMax(maxValue.getAsInt());
            }
            filters.add(intFilter);
        });
        doubleRange.forEach((key, value) -> {
            RangeFilter<Double> doubleFilter = new RangeFilter<>();
            doubleFilter.setName(key);
            doubleFilter.setParameterName(value);
            doubleFilter.setType("range");
            OptionalDouble minValue = productDoubleStream(products, value).min();
            if(minValue.isPresent()) {
                doubleFilter.setMin(minValue.getAsDouble());
            }
            OptionalDouble maxValue = productDoubleStream(products, value).max();
            if(maxValue.isPresent()) {
                doubleFilter.setMax(maxValue.getAsDouble());
            }
            filters.add(doubleFilter);
        });
        return filters;
    }
    public static <T extends Product> List<String> inFilterValues(List<T> products, String value) {
        return products.stream()
                .filter(product -> getProductAdditional(product) != null)
                .filter(product -> getProductAdditional(product).getFieldValue(value) != null)
                .filter((distinctByKey(product -> getProductAdditional(product).getFieldValue(value))))
                .map(product -> getProductAdditional(product).getFieldValue(value) + "").toList();

    }
    public static <T extends Product> IntStream productIntStream(List<T> products, String value) {
       return products.stream()
                .filter(product -> getProductValue(product) != null && getProductAdditional(product) != null)
                .filter(product -> getProductValue(product).getFieldValue(value) != null)
                .mapToInt(product -> (int) getProductValue(product).getFieldValue(value));
    }
    public static <T extends Product> DoubleStream productDoubleStream(List<T> products, String value) {
        return products.stream()
                .filter(product -> getProductValue(product) != null && getProductAdditional(product) != null)
                .filter(product -> getProductValue(product).getFieldValue(value) != null)
                .mapToDouble(product -> (double) getProductValue(product).getFieldValue(value));
    }
    public static <T extends Product> Values getProductValue(T product) {
        if(product instanceof CPU) {
            return ((CPU) product).getValues();
        }
        if(product instanceof GraphicCard) {
            return ((GraphicCard) product).getValues();
        }
        if(product instanceof Chassis) {
            return ((Chassis) product).getValues();
        }
        if(product instanceof PSU) {
            return ((PSU) product).getValues();
        }
        if(product instanceof Motherboard) {
            return ((Motherboard) product).getValues();
        }
        if(product instanceof Ram) {
            return ((Ram) product).getValues();
        }
        if(product instanceof SSD) {
            return ((SSD) product).getValues();
        }
        if(product instanceof HDD) {
            return ((HDD) product).getValues();
        }
        if(product instanceof Fan) {
            return ((Fan) product).getValues();
        }
        return null;
    }
    public static <T extends Product> Additional getProductAdditional(T product) {
        if(product instanceof CPU) {
            return ((CPU) product).getAdditional();
        }
        if(product instanceof GraphicCard) {
            return ((GraphicCard) product).getAdditional();
        }
        if(product instanceof Chassis) {
            return ((Chassis) product).getAdditional();
        }
        if(product instanceof PSU) {
            return ((PSU) product).getAdditional();
        }
        if(product instanceof Motherboard) {
            return ((Motherboard) product).getAdditional();
        }
        if(product instanceof Ram) {
            return ((Ram) product).getAdditional();
        }
        if(product instanceof SSD) {
            return ((SSD) product).getAdditional();
        }
        if(product instanceof HDD) {
            return ((HDD) product).getAdditional();
        }
        if(product instanceof Fan) {
            return ((Fan) product).getAdditional();
        }
        return null;
    }
    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

}
