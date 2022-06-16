package com.onliner.tocks.repository;

import com.onliner.tocks.model.product.Motherboard;
import com.onliner.tocks.repository.custom.MotherboardRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotherboardRepository extends MongoRepository<Motherboard, String>, MotherboardRepositoryCustom
{
    List<Motherboard> findAllByPositionsNotNull();
}
