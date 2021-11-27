package com.onliner.tocks.controller;

import com.onliner.tocks.service.GraphicCardService;
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
public class GraphicCardController
{
    private final GraphicCardService graphicCardService;

    public GraphicCardController(GraphicCardService graphicCardService)
    {
        this.graphicCardService = graphicCardService;
    }

    @GetMapping("/gpu")
    public ResponseEntity<Map<String, Object>> findAllGraphicCardsPageable(@PageableDefault(size = 100) Pageable pageable, HttpServletRequest request) {
        return request.getParameter("page") != null && !request.getParameter("page").equals("0") ? graphicCardService.findAllBySellersNotNull(pageable,request.getRequestURL().toString()) : graphicCardService.findAllBySellersNotNull();
    }
    @GetMapping("/gpu/{id}")
    public ResponseEntity<Object> findGraphicCardById(@PathVariable String id){
        return graphicCardService.findCPUById(id);
    }
}
