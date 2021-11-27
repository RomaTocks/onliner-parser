package com.onliner.tocks.service.implementation;

import com.onliner.tocks.model.CPU;
import com.onliner.tocks.repository.CPURepository;
import com.onliner.tocks.response.exception.RequestException;
import com.onliner.tocks.service.CpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.onliner.tocks.response.Response.configureResponse;

@Service
public class CPUServiceImpl implements CpuService
{
    private final CPURepository repository;
    @Autowired
    public CPUServiceImpl(CPURepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CPU> findAll() {
        return repository.findAll();
    }

    @Override
    public List<CPU> findAllByPositionsNotNull()
    {
        return repository.findAllByPositionsNotNull();
    }

    @Override
    public ResponseEntity<Map<String, Object>> findAllBySellersNotNull() {
        HashMap<String, Object> map = new HashMap<>();
        List<CPU> list = repository.findAllByPositionsNotNull();
        map.put("products", list);
        map.put("total", (long) list.size());
        return new ResponseEntity<>(map,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<String, Object>> findAllBySellersNotNull(Pageable pageable, String URL) {
        Page<CPU> page = repository.findAllByPositionsNotNull(pageable);
        return configureResponse(page,URL);
    }

    @Override
    public ResponseEntity<Object> findCPUById(String id) {
        if(repository.findById(id).isPresent()) {
            return new ResponseEntity<>(repository.findById(id).get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new RequestException("Продукт с id : " + id + " не найден."), HttpStatus.OK);
        }
    }

    @Override
    public void saveAll(List<CPU> cpus) {
        repository.saveAll(cpus);
    }
}
