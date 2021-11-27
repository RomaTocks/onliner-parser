package com.onliner.tocks.controller;

import com.onliner.tocks.service.FanService;
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
public class FanController
{
    private final FanService fanService;

    public FanController(FanService fanService)
    {
        this.fanService = fanService;
    }

    @GetMapping("/fan")
    public ResponseEntity<Map<String, Object>> findAllFanPageable(@PageableDefault(size = 100) Pageable pageable, HttpServletRequest request) {
        return request.getParameter("page") != null && !request.getParameter("page").equals("0") ? fanService.findAllBySellersNotNull(pageable,request.getRequestURL().toString()) : fanService.findAllBySellersNotNull();
    }
    @GetMapping("/fan/{id}")
    public ResponseEntity<Object> findFanById(@PathVariable String id){
        return fanService.findCPUById(id);
    }
}
