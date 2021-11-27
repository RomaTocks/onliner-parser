package com.onliner.tocks.service.implementation;

import com.onliner.tocks.model.SSD;
import com.onliner.tocks.repository.SSDRepository;
import com.onliner.tocks.response.exception.RequestException;
import com.onliner.tocks.service.SSDService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.onliner.tocks.response.Response.configureResponse;

@Service
public class SSDServiceImpl implements SSDService
{
    private final SSDRepository ssdRepository;

    public SSDServiceImpl(SSDRepository ssdRepository)
    {
        this.ssdRepository = ssdRepository;
    }

    @Override
    public List<SSD> findAll()
    {
        return ssdRepository.findAll();
    }

    @Override
    public List<SSD> findAllByPositionsNotNull()
    {
        return ssdRepository.findAllByPositionsNotNull();
    }

    @Override
    public ResponseEntity<Map<String, Object>> findAllBySellersNotNull()
    {
        HashMap<String, Object> map = new HashMap<>();
        List<SSD> list = ssdRepository.findAllByPositionsNotNull();
        map.put("products", list);
        map.put("total", (long) list.size());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<String, Object>> findAllBySellersNotNull(Pageable pageable, String URL)
    {
        return configureResponse(ssdRepository.findAllByPositionsNotNull(pageable),URL);
    }

    @Override
    public ResponseEntity<Object> findCPUById(String id)
    {
        if(ssdRepository.findById(id).isPresent()) {
            return new ResponseEntity<>(ssdRepository.findById(id).get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new RequestException("Продукт с id : " + id + " не найден."), HttpStatus.OK);
        }
    }

    @Override
    public void saveAll(List<SSD> ssds)
    {
        ssdRepository.saveAll(ssds);
    }
}
