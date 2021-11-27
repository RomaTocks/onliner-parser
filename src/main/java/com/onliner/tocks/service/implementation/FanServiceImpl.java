package com.onliner.tocks.service.implementation;

import com.onliner.tocks.model.Fan;
import com.onliner.tocks.repository.FanRepository;
import com.onliner.tocks.response.exception.RequestException;
import com.onliner.tocks.service.FanService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.onliner.tocks.response.Response.configureResponse;

@Service
public class FanServiceImpl implements FanService
{
    private final FanRepository fanRepository;

    public FanServiceImpl(FanRepository fanRepository)
    {
        this.fanRepository = fanRepository;
    }

    @Override
    public List<Fan> findAll()
    {
        return fanRepository.findAll();
    }

    @Override
    public List<Fan> findAllByPositionsNotNull()
    {
        return fanRepository.findAllByPositionsNotNull();
    }

    @Override
    public ResponseEntity<Map<String, Object>> findAllBySellersNotNull()
    {
        HashMap<String, Object> map = new HashMap<>();
        List<Fan> list = fanRepository.findAllByPositionsNotNull();
        map.put("products", list);
        map.put("total", (long) list.size());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<String, Object>> findAllBySellersNotNull(Pageable pageable, String URL)
    {
        return configureResponse(fanRepository.findAllByPositionsNotNull(pageable),URL);
    }

    @Override
    public ResponseEntity<Object> findCPUById(String id)
    {
        if(fanRepository.findById(id).isPresent()) {
            return new ResponseEntity<>(fanRepository.findById(id).get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new RequestException("Продукт с id : " + id + " не найден."), HttpStatus.OK);
        }
    }

    @Override
    public void saveAll(List<Fan> fans)
    {
        fanRepository.saveAll(fans);
    }
}
