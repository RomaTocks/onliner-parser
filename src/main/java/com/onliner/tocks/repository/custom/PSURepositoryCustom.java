package com.onliner.tocks.repository.custom;

import com.onliner.tocks.model.filters.Filter;
import com.onliner.tocks.model.product.PSU;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface PSURepositoryCustom
{
    List<Filter> filters();
}
