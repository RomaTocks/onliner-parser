package com.onliner.tocks.controller;

import com.onliner.tocks.service.CpuService;
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
public class ProcessorController
{
    private final CpuService service;

    public ProcessorController(CpuService service) {
        this.service = service;
    }

    @GetMapping("/cpu")
    public ResponseEntity<Map<String, Object>> findAllCPUPageable(@PageableDefault(size = 100) Pageable pageable, HttpServletRequest request) {
        return request.getParameter("page") != null && !request.getParameter("page").equals("0") ? service.findAllBySellersNotNull(pageable,request.getRequestURL().toString()) : service.findAllBySellersNotNull();
    }
    @GetMapping("/cpu/{id}")
    public ResponseEntity<Object> findCPUById(@PathVariable String id){
        return service.findCPUById(id);
    }
}
