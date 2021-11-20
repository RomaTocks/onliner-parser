package com.onliner.tocks.parsing.common.sellers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Positions {
    private List<Position> primary;
}
