package com.onliner.tocks.repository;

import com.onliner.tocks.model.product.PSU;
import com.onliner.tocks.repository.custom.PSURepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PSURepository extends MongoRepository<PSU,String>, PSURepositoryCustom
{
    List<PSU> findAllByPositionsNotNull();
}
