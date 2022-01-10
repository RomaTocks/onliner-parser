package com.onliner.tocks.parsing.filters.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.onliner.tocks.parsing.filters.Facet;
import com.onliner.tocks.parsing.filters.ProductFilters;
import com.onliner.tocks.parsing.filters.items.Item;
import com.onliner.tocks.parsing.filters.items.Placeholder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Slf4j
public class FilterDeserializer extends StdDeserializer<ProductFilters>
{
    protected FilterDeserializer(Class<?> vc)
    {
        super(vc);
    }
    public  FilterDeserializer() {
        this(null);
    }

    @Override
    public ProductFilters deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
        ProductFilters productFilters = new ProductFilters();
        try {
            productFilters.setFacets(mapper.readValue(jsonNode.get("facets").toString(), Facet.class));
        }
        catch (JsonProcessingException e)
        {
            log.warn("Facets type mismatch.");
            productFilters.setFacets(new Facet());
        }
        try {
            TypeReference<HashMap<String, Placeholder>> reference = new TypeReference<>() {};
            productFilters.setPlaceholders(mapper.readValue(jsonNode.get("placeholders").toString(),reference));
        }
        catch (Exception e)
        {
            log.warn("Placeholders type mismatch.");
            productFilters.setPlaceholders(new HashMap<>());
        }
        try {
            TypeReference<HashMap<String, List<Item>>> reference = new TypeReference<>() {};
            productFilters.setDictionaries(mapper.readValue(jsonNode.get("dictionaries").toString(),reference));
        }
        catch (JsonProcessingException e)
        {
            log.warn("Dictionaries type mismatch.");
            productFilters.setDictionaries(new HashMap<>());
        }

        return productFilters;
    }
}
