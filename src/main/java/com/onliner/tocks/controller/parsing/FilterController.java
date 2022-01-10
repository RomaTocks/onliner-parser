package com.onliner.tocks.controller.parsing;

import com.onliner.tocks.parsing.Parser;
import com.onliner.tocks.parsing.filters.ProductFilters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/filters")
public class FilterController
{
    private final Parser parser;

    @Autowired
    public FilterController(Parser parser)
    {
        this.parser = parser;
    }

    @GetMapping
    public List<ProductFilters> getProductFilters() {
        return parser.parseProductFilters();
    }
}
