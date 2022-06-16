package com.onliner.tocks.repository;


import com.onliner.tocks.model.product.CPU;
import com.onliner.tocks.repository.custom.CPURepositoryCustom;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CPURepository extends MongoRepository<CPU, String>, CPURepositoryCustom
{
    List<CPU> findAllByPositionsNotNull();
}
