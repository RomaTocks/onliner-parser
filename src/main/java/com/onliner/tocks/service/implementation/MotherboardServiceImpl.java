package com.onliner.tocks.service.implementation;

import com.onliner.tocks.model.Motherboard;
import com.onliner.tocks.repository.MotherboardRepository;
import com.onliner.tocks.response.exception.RequestException;
import com.onliner.tocks.service.MotherboardService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.onliner.tocks.response.Response.configureResponse;

@Service
public class MotherboardServiceImpl implements MotherboardService
{
    private final MotherboardRepository motherboardRepository;

    public MotherboardServiceImpl(MotherboardRepository motherboardRepository)
    {
        this.motherboardRepository = motherboardRepository;
    }

    @Override
    public List<Motherboard> findAll()
    {
        return motherboardRepository.findAll();
    }

    @Override
    public List<Motherboard> findAllByPositionsNotNull()
    {
        return motherboardRepository.findAllByPositionsNotNull();
    }

    @Override
    public ResponseEntity<Map<String, Object>> findAllBySellersNotNull()
    {
        HashMap<String, Object> map = new HashMap<>();
        List<Motherboard> list = motherboardRepository.findAllByPositionsNotNull();
        map.put("products", list);
        map.put("total", (long) list.size());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<String, Object>> findAllBySellersNotNull(Pageable pageable, String URL)
    {
        return configureResponse(motherboardRepository.findAllByPositionsNotNull(pageable),URL);
    }

    @Override
    public ResponseEntity<Object> findCPUById(String id)
    {
        if(motherboardRepository.findById(id).isPresent()) {
            return new ResponseEntity<>(motherboardRepository.findById(id).get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new RequestException("Продукт с id : " + id + " не найден."), HttpStatus.OK);
        }
    }

    @Override
    public void saveAll(List<Motherboard> motherboards)
    {
        motherboardRepository.saveAll(motherboards);
    }
}
