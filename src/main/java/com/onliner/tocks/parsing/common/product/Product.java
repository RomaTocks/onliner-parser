package com.onliner.tocks.parsing.common.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
        this.setPrices(product.getPrices());
        this.setPositions(product.getPositions());
    }
}
