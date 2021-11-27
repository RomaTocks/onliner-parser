package com.onliner.tocks.repository;

import com.onliner.tocks.model.Fan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FanRepository extends MongoRepository<Fan, String> {
    List<Fan> findAllByPositionsNotNull();
    Page<Fan> findAllByPositionsNotNull(Pageable pageable);
}
