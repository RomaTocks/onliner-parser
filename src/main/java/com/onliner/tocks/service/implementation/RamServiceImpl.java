package com.onliner.tocks.service.implementation;

import com.onliner.tocks.model.Ram;
import com.onliner.tocks.repository.RamRepository;
import com.onliner.tocks.response.exception.RequestException;
import com.onliner.tocks.service.RamService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.onliner.tocks.response.Response.configureResponse;

@Service
public class RamServiceImpl implements RamService
{
    private final RamRepository ramRepository;

    public RamServiceImpl(RamRepository ramRepository)
    {
        this.ramRepository = ramRepository;
    }

    @Override
    public List<Ram> findAll()
    {
        return ramRepository.findAll();
    }

    @Override
    public List<Ram> findAllByPositionsNotNull()
    {
        return ramRepository.findAllByPositionsNotNull();
    }

    @Override
    public ResponseEntity<Map<String, Object>> findAllBySellersNotNull()
    {
        HashMap<String, Object> map = new HashMap<>();
        List<Ram> list = ramRepository.findAllByPositionsNotNull();
        map.put("products", list);
        map.put("total", (long) list.size());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<String, Object>> findAllBySellersNotNull(Pageable pageable, String URL)
    {
        return configureResponse(ramRepository.findAllByPositionsNotNull(pageable),URL);
    }

    @Override
    public ResponseEntity<Object> findCPUById(String id)
    {
        if(ramRepository.findById(id).isPresent()) {
            return new ResponseEntity<>(ramRepository.findById(id).get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new RequestException("Продукт с id : " + id + " не найден."), HttpStatus.OK);
        }
    }

    @Override
    public void saveAll(List<Ram> rams)
    {
        ramRepository.saveAll(rams);
    }
}
