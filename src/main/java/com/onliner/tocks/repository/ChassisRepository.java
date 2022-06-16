package com.onliner.tocks.repository;

import com.onliner.tocks.model.product.Chassis;
import com.onliner.tocks.repository.custom.ChassisRepositoryCustom;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChassisRepository extends MongoRepository<Chassis, String>, ChassisRepositoryCustom
{
    List<Chassis> findAllByPositionsNotNull();
}
