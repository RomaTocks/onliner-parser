package com.onliner.tocks.service;

import com.onliner.tocks.model.product.SSD;

import java.util.List;

public interface SSDService {
    List<SSD> findAll();
    List<SSD> findAllByPositionsNotNull();
    void saveAll(List<SSD> ssds);
}
