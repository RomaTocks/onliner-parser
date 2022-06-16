package com.onliner.tocks.repository;

import com.onliner.tocks.model.product.HDD;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HDDRepository extends MongoRepository<HDD,String>
{
    List<HDD> findAllByPositionsNotNull();
}
