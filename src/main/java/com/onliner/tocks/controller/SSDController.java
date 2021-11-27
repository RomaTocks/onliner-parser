package com.onliner.tocks.controller;

import com.onliner.tocks.service.SSDService;
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
public class SSDController
{
    private final SSDService ssdService;

    public SSDController(SSDService ssdService)
    {
        this.ssdService = ssdService;
    }
    @GetMapping("/ssd")
    public ResponseEntity<Map<String, Object>> findAllSSDPageable(@PageableDefault(page = 1, size = 100) Pageable pageable, HttpServletRequest request) {
        return request.getParameter("page") != null && !request.getParameter("page").equals("0") ? ssdService.findAllBySellersNotNull(pageable,request.getRequestURL().toString()) : ssdService.findAllBySellersNotNull();
    }
    @GetMapping("/ssd/{id}")
    public ResponseEntity<Object> findSSDById(@PathVariable String id){
        return ssdService.findCPUById(id);
    }
}
