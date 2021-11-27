package com.onliner.tocks.controller;

import com.onliner.tocks.service.MotherboardService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MotherboardController
{
    private final MotherboardService motherboardService;

    public MotherboardController(MotherboardService motherboardService)
    {
        this.motherboardService = motherboardService;
    }
    @GetMapping("/motherboard")
    public ResponseEntity<Map<String, Object>> findAllMotherboardsPageable(@PageableDefault(size = 100) Pageable pageable, HttpServletRequest request) {
        return request.getParameter("page") != null && !request.getParameter("page").equals("0") ? motherboardService.findAllBySellersNotNull(pageable,request.getRequestURL().toString()) : motherboardService.findAllBySellersNotNull();
    }
    @GetMapping("/motherboard/{id}")
    public ResponseEntity<Object> findMotherboardById(@PathVariable String id){
        return motherboardService.findCPUById(id);
    }
}
