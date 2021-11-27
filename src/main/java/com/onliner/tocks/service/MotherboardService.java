package com.onliner.tocks.service;

import com.onliner.tocks.model.Motherboard;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface MotherboardService {
    List<Motherboard> findAll();
    List<Motherboard> findAllByPositionsNotNull();
    ResponseEntity<Map<String,Object>> findAllBySellersNotNull();
    ResponseEntity<Map<String,Object>> findAllBySellersNotNull(Pageable pageable, String URL);
    ResponseEntity<Object> findCPUById(String id);
    void saveAll(List<Motherboard> motherboards);
}
