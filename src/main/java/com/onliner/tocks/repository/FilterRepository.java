package com.onliner.tocks.repository;

import com.onliner.tocks.parsing.filters.ProductFilters;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilterRepository extends MongoRepository<ProductFilters, String> {
}
