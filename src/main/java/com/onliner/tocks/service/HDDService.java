package com.onliner.tocks.service;

import com.onliner.tocks.model.product.HDD;

import java.util.List;

public interface HDDService {
    List<HDD> findAll();
    List<HDD> findAllByPositionsNotNull();
    void saveAll(List<HDD> hdds);
}
