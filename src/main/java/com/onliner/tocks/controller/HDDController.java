package com.onliner.tocks.controller;

import com.onliner.tocks.service.HDDService;
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
public class HDDController
{
    private final HDDService hddService;

    public HDDController(HDDService hddService)
    {
        this.hddService = hddService;
    }

    @GetMapping("/hdd")
    public ResponseEntity<Map<String, Object>> findAllHDDPageable(@PageableDefault(size = 100) Pageable pageable, HttpServletRequest request) {
        return request.getParameter("page") != null && !request.getParameter("page").equals("0") ? hddService.findAllBySellersNotNull(pageable,request.getRequestURL().toString()) : hddService.findAllBySellersNotNull();
    }
    @GetMapping("/hdd/{id}")
    public ResponseEntity<Object> findHDDById(@PathVariable String id){
        return hddService.findCPUById(id);
    }
}
