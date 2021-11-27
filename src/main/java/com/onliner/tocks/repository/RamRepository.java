package com.onliner.tocks.repository;

import com.onliner.tocks.model.Ram;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RamRepository extends MongoRepository<Ram, String>
{
    List<Ram> findAllByPositionsNotNull();
    Page<Ram> findAllByPositionsNotNull(Pageable pageable);
}
