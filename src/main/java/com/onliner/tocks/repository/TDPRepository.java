package com.onliner.tocks.repository;

import com.onliner.tocks.parsing.common.tdp.TDP;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TDPRepository extends MongoRepository<TDP,String> {
}
