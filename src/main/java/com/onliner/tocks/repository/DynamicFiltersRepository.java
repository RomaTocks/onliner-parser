package com.onliner.tocks.repository;

import com.onliner.tocks.model.filters.DynamicFilters;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DynamicFiltersRepository extends MongoRepository<DynamicFilters, String> {}
