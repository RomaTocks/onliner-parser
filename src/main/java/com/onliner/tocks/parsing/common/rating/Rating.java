package com.onliner.tocks.parsing.common.rating;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Rating
{
    private Integer rating;
    private Integer count;
}
