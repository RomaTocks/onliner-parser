package com.onliner.tocks.parsing.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Page {
    private Integer current;
    private Integer items;
    private Integer last;
    private Integer limit;
    private Integer total;
    public void incrementCurrent() {
        current++;
    }
}
