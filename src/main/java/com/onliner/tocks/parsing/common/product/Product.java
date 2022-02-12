package com.onliner.tocks.parsing.common.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.onliner.tocks.parsing.common.price.Price;
import com.onliner.tocks.parsing.common.price.Prices;
import com.onliner.tocks.parsing.common.sellers.Position;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Product {
    @Id
    protected String id;
    protected String key;
    protected String name;
    protected Manufacturer manufacturer;
    protected String description;
    protected Prices prices;
    protected String html_url;
    protected List<Position> positions;
    private Integer total;

    public void setInformation(Product product) {
        this.setId(product.getId());
        this.setKey(product.getKey());
        this.setDescription(product.getDescription());
        this.setHtml_url(product.getHtml_url());
        this.setName(product.getName());
        this.setPrices(priceWithValues(product.getPrices()));
        this.setPositions(product.getPositions());
        this.setManufacturer(product.getManufacturer());
    }
    private Prices priceWithValues(Prices prices) {
        Price minimalPrice = prices.getPrice_min();
        Price maxPrice = prices.getPrice_max();
        minimalPrice.setValue(Double.parseDouble(minimalPrice.getAmount()));
        maxPrice.setValue(Double.parseDouble(maxPrice.getAmount()));
        prices.setPrice_min(minimalPrice);
        prices.setPrice_max(maxPrice);
        return prices;
    }
}
