package com.onliner.tocks.repository;

import com.onliner.tocks.model.product.GraphicCard;
import com.onliner.tocks.repository.custom.GraphicCardRepositoryCustom;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GraphicCardRepository extends MongoRepository<GraphicCard,String>, GraphicCardRepositoryCustom
{
    List<GraphicCard> findAllByPositionsNotNull();
}
