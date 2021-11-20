package com.onliner.tocks.parsing.common.sellers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.HashMap;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Sellers {
    private Positions positions;
    private HashMap<String, Shop> shops;
}
