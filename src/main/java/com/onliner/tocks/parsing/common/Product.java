package com.onliner.tocks.parsing.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.onliner.tocks.parsing.common.sellers.Sellers;
import lombok.Data;
import org.springframework.data.annotation.Id;

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
    protected Sellers sellers;
    private Integer total;

    public void setInformation(Product product) {
        this.setId(product.getId());
        this.setKey(product.getKey());
        this.setDescription(product.getDescription());
        this.setHtml_url(product.getHtml_url());
        this.setName(product.getName());
        this.setPrices(product.getPrices());
        this.setSellers(product.getSellers());
    }
}
