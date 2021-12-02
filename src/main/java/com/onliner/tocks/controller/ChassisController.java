package com.onliner.tocks.controller;

import com.onliner.tocks.service.ChassisService;
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
public class ChassisController
{
    private final ChassisService chassisService;

    public ChassisController(ChassisService chassisService)
    {
        this.chassisService = chassisService;
    }
    @GetMapping("/chassis")
    public ResponseEntity<Map<String, Object>> findAllChassisPageable(@PageableDefault(page = 1, size = 100) Pageable pageable, HttpServletRequest request) {
        return request.getParameter("page") != null && !request.getParameter("page").equals("0") ? chassisService.findAllBySellersNotNull(pageable,request.getRequestURL().toString()) : chassisService.findAllBySellersNotNull();
    }
    @GetMapping("/chassis/{id}")
    public ResponseEntity<Object> findChassisById(@PathVariable String id){
        return chassisService.findCPUById(id);
    }
}
