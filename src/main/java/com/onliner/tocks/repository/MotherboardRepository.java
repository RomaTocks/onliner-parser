package com.onliner.tocks.repository;

import com.onliner.tocks.model.Motherboard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotherboardRepository extends MongoRepository<Motherboard, String>
{
    List<Motherboard> findAllByPositionsNotNull();
    Page<Motherboard> findAllByPositionsNotNull(Pageable pageable);
}
