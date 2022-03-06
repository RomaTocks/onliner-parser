package com.onliner.tocks.parsing.common.sellers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.onliner.tocks.parsing.common.rating.Rating;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Shop {
    private Integer id;
    private String title;
    private String html_url;
    private String logo;
    private Rating reviews;
}
