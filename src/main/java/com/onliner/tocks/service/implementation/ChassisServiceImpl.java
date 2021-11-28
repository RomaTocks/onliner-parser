package com.onliner.tocks.service.implementation;

import com.onliner.tocks.model.Chassis;
import com.onliner.tocks.repository.ChassisRepository;
import com.onliner.tocks.response.exception.RequestException;
import com.onliner.tocks.service.ChassisService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.onliner.tocks.response.Response.configureResponse;

@Service
public class ChassisServiceImpl implements ChassisService
{
    private final ChassisRepository chassisRepository;

    public ChassisServiceImpl(ChassisRepository chassisRepository)
    {
        this.chassisRepository = chassisRepository;
    }

    @Override
    public List<Chassis> findAll()
    {
        return chassisRepository.findAll();
    }

    @Override
    public List<Chassis> findAllByPositionsNotNull()
    {
        return chassisRepository.findAllByPositionsNotNull();
    }

    @Override
    public ResponseEntity<Map<String, Object>> findAllBySellersNotNull()
    {
        HashMap<String, Object> map = new HashMap<>();
        List<Chassis> list = chassisRepository.findAllByPositionsNotNull();
        map.put("products", list);
        map.put("total", (long) list.size());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<String, Object>> findAllBySellersNotNull(Pageable pageable, String URL)
    {
        return configureResponse(chassisRepository.findAllByPositionsNotNull(pageable),URL);
    }

    @Override
    public ResponseEntity<Object> findCPUById(String id)
    {
        if(chassisRepository.findById(id).isPresent()) {
            return new ResponseEntity<>(chassisRepository.findById(id).get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new RequestException("Продукт с id : " + id + " не найден."), HttpStatus.OK);
        }
    }

    @Override
    public void saveAll(List<Chassis> chassis)
    {
        chassisRepository.saveAll(chassis);
    }
}
