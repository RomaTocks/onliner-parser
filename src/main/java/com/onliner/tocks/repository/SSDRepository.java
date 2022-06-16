package com.onliner.tocks.repository;

import com.onliner.tocks.model.product.SSD;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SSDRepository extends MongoRepository<SSD,String>
{
    List<SSD> findAllByPositionsNotNull();
}
