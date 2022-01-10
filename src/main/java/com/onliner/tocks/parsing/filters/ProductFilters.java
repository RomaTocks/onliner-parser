package com.onliner.tocks.parsing.filters;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.onliner.tocks.parsing.filters.deserializer.FilterDeserializer;
import com.onliner.tocks.parsing.filters.items.Item;
import com.onliner.tocks.parsing.filters.items.Placeholder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = FilterDeserializer.class)
@Document(collection = "filters")
@Data
public class ProductFilters
{
    @Id
    private String id;
    private Facet facets;
    private Map<String, Placeholder> placeholders;
    private Map<String, List<Item>> dictionaries;
}
