package com.onliner.tocks.controller;

import com.onliner.tocks.service.PSUService;
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
public class PSUController
{
    private final PSUService psuService;

    public PSUController(PSUService psuService)
    {
        this.psuService = psuService;
    }
    @GetMapping("/psu")
    public ResponseEntity<Map<String, Object>> findAllPSUPageable(@PageableDefault(page = 1, size = 100) Pageable pageable, HttpServletRequest request) {
        return request.getParameter("page") != null && !request.getParameter("page").equals("0") ? psuService.findAllBySellersNotNull(pageable,request.getRequestURL().toString()) : psuService.findAllBySellersNotNull();
    }
    @GetMapping("/psu/{id}")
    public ResponseEntity<Object> findPSUById(@PathVariable String id){
        return psuService.findCPUById(id);
    }
}
