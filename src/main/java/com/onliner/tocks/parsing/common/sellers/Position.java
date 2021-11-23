package com.onliner.tocks.parsing.common.sellers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.onliner.tocks.parsing.common.Price;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Position {
    private String id;
    private String article;
    private Integer shop_id;
    private Price position_price;
    private String importer;
    private String html_url;
    private String title;
    private String logo;

    public void setShopInformation(Shop shop) {
        this.setHtml_url(shop.getHtml_url());
        this.setLogo(shop.getLogo());
        this.setTitle(shop.getTitle());
    }
}
