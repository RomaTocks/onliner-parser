package com.onliner.tocks.service.implementation;

import com.onliner.tocks.model.PSU;
import com.onliner.tocks.repository.PSURepository;
import com.onliner.tocks.response.exception.RequestException;
import com.onliner.tocks.service.PSUService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.onliner.tocks.response.Response.configureResponse;

@Service
public class PSUServiceImpl implements PSUService
{
    private final PSURepository psuRepository;

    public PSUServiceImpl(PSURepository psuRepository)
    {
        this.psuRepository = psuRepository;
    }

    @Override
    public List<PSU> findAll()
    {
        return psuRepository.findAll();
    }

    @Override
    public List<PSU> findAllByPositionsNotNull()
    {
        return psuRepository.findAllByPositionsNotNull();
    }

    @Override
    public ResponseEntity<Map<String, Object>> findAllBySellersNotNull()
    {
        HashMap<String, Object> map = new HashMap<>();
        List<PSU> list = psuRepository.findAllByPositionsNotNull();
        map.put("products", list);
        map.put("total", (long) list.size());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<String, Object>> findAllBySellersNotNull(Pageable pageable, String URL)
    {
        return configureResponse(psuRepository.findAllByPositionsNotNull(pageable),URL);
    }

    @Override
    public ResponseEntity<Object> findCPUById(String id)
    {
        if(psuRepository.findById(id).isPresent()) {
            return new ResponseEntity<>(psuRepository.findById(id).get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new RequestException("Продукт с id : " + id + " не найден."), HttpStatus.OK);
        }
    }

    @Override
    public void saveAll(List<PSU> psus)
    {
        psuRepository.saveAll(psus);
    }
}
