package com.onliner.tocks.repository;

import com.onliner.tocks.model.GraphicCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GraphicCardRepository extends MongoRepository<GraphicCard,String>
{
    List<GraphicCard> findAllByPositionsNotNull();
    Page<GraphicCard> findAllByPositionsNotNull(Pageable pageable);
}
