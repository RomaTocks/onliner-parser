package com.onliner.tocks.controller;

import com.onliner.tocks.service.RamService;
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
public class RamController
{
    private final RamService ramService;

    public RamController(RamService ramService)
    {
        this.ramService = ramService;
    }
    @GetMapping("/ram")
    public ResponseEntity<Map<String, Object>> findAllRamPageable(@PageableDefault(size = 100) Pageable pageable, HttpServletRequest request) {
        return request.getParameter("page") != null && !request.getParameter("page").equals("0") ? ramService.findAllBySellersNotNull(pageable,request.getRequestURL().toString()) : ramService.findAllBySellersNotNull();
    }
    @GetMapping("/ram/{id}")
    public ResponseEntity<Object> findRamById(@PathVariable String id){
        return ramService.findCPUById(id);
    }
}