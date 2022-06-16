package com.onliner.tocks.repository;

import com.onliner.tocks.model.product.Fan;
import com.onliner.tocks.repository.custom.FanRepositoryCustom;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FanRepository extends MongoRepository<Fan, String>, FanRepositoryCustom
{
    List<Fan> findAllByPositionsNotNull();
}
