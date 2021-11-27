package com.onliner.tocks.service.implementation;

import com.onliner.tocks.model.HDD;
import com.onliner.tocks.repository.HDDRepository;
import com.onliner.tocks.response.exception.RequestException;
import com.onliner.tocks.service.HDDService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.onliner.tocks.response.Response.configureResponse;

@Service
public class HDDServiceImpl implements HDDService
{
    private final HDDRepository hddRepository;

    public HDDServiceImpl(HDDRepository hddRepository)
    {
        this.hddRepository = hddRepository;
    }

    @Override
    public List<HDD> findAll()
    {
        return hddRepository.findAll();
    }

    @Override
    public List<HDD> findAllByPositionsNotNull()
    {
        return hddRepository.findAllByPositionsNotNull();
    }

    @Override
    public ResponseEntity<Map<String, Object>> findAllBySellersNotNull()
    {
        HashMap<String, Object> map = new HashMap<>();
        List<HDD> list = hddRepository.findAllByPositionsNotNull();
        map.put("products", list);
        map.put("total", (long) list.size());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<String, Object>> findAllBySellersNotNull(Pageable pageable, String URL)
    {
        return configureResponse(hddRepository.findAllByPositionsNotNull(pageable),URL);
    }

    @Override
    public ResponseEntity<Object> findCPUById(String id)
    {
        if(hddRepository.findById(id).isPresent()) {
            return new ResponseEntity<>(hddRepository.findById(id).get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new RequestException("Продукт с id : " + id + " не найден."), HttpStatus.OK);
        }
    }

    @Override
    public void saveAll(List<HDD> hdds)
    {
        hddRepository.saveAll(hdds);
    }
}
