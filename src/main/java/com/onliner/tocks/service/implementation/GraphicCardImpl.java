package com.onliner.tocks.service.implementation;

import com.onliner.tocks.model.GraphicCard;
import com.onliner.tocks.repository.GraphicCardRepository;
import com.onliner.tocks.response.exception.RequestException;
import com.onliner.tocks.service.GraphicCardService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.onliner.tocks.response.Response.configureResponse;

@Service
public class GraphicCardImpl implements GraphicCardService
{
    private final GraphicCardRepository graphicCardRepository;

    public GraphicCardImpl(GraphicCardRepository graphicCardRepository)
    {
        this.graphicCardRepository = graphicCardRepository;
    }

    @Override
    public List<GraphicCard> findAll()
    {
        return graphicCardRepository.findAll();
    }

    @Override
    public List<GraphicCard> findAllByPositionsNotNull()
    {
        return graphicCardRepository.findAllByPositionsNotNull();
    }

    @Override
    public ResponseEntity<Map<String, Object>> findAllBySellersNotNull()
    {
        HashMap<String, Object> map = new HashMap<>();
        List<GraphicCard> list = graphicCardRepository.findAllByPositionsNotNull();
        map.put("products", list);
        map.put("total", (long) list.size());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<String, Object>> findAllBySellersNotNull(Pageable pageable, String URL)
    {
        return configureResponse(graphicCardRepository.findAllByPositionsNotNull(pageable),URL);
    }

    @Override
    public ResponseEntity<Object> findCPUById(String id)
    {
        if(graphicCardRepository.findById(id).isPresent()) {
            return new ResponseEntity<>(graphicCardRepository.findById(id).get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new RequestException("Продукт с id : " + id + " не найден."), HttpStatus.OK);
        }
    }

    @Override
    public void saveAll(List<GraphicCard> graphicCards)
    {
        graphicCardRepository.saveAll(graphicCards);
    }
}
