package com.onliner.tocks.repository;


import com.onliner.tocks.model.CPU;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CPURepository extends MongoRepository<CPU, String> {
    List<CPU> findAllByPositionsNotNull();
    Page<CPU> findAllByPositionsNotNull(Pageable pageable);
}
