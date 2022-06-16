package com.onliner.tocks.controller;

import com.onliner.tocks.model.filters.Filter;
import com.onliner.tocks.service.*;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("filters/")
public class FilterController
{
    private final CpuService cpuService;
    private final GraphicCardService graphicCardService;
    private final FanService fanService;
    private final HDDService hddService;
    private final SSDService ssdService;
    private final PSUService psuService;
    private final MotherboardService motherboardService;
    private final RamService ramService;
    private final ChassisService chassisService;
    private final DynamicFiltersService dynamicFiltersService;

    public FilterController(CpuService cpuService, GraphicCardService graphicCardService, FanService fanService, HDDService hddService, SSDService ssdService, PSUService psuService, MotherboardService motherboardService, RamService ramService, ChassisService chassisService, DynamicFiltersService dynamicFiltersService)
    {
        this.cpuService = cpuService;
        this.graphicCardService = graphicCardService;
        this.fanService = fanService;
        this.hddService = hddService;
        this.ssdService = ssdService;
        this.psuService = psuService;
        this.motherboardService = motherboardService;
        this.ramService = ramService;
        this.chassisService = chassisService;
        this.dynamicFiltersService = dynamicFiltersService;
    }
    @PutMapping("/cpu")
    public void updateProcessorsFilters() {
        List<Filter> filters = cpuService.dynamicFilters();
        dynamicFiltersService.saveFilters(filters, "cpu");
    }
    @PutMapping("/gpu")
    public void updateGraphicCardFilters() {
        List<Filter> filters = graphicCardService.dynamicFilters();
        dynamicFiltersService.saveFilters(filters, "gpu");
    }
    @PutMapping("/chassis")
    public void updateChassisFilters() {
        List<Filter> filters = chassisService.dynamicFilters();
        dynamicFiltersService.saveFilters(filters, "chassis");
    }
    @PutMapping("/motherboard")
    public void updateMotherboardFilters() {
        List<Filter> filters = motherboardService.dynamicFilters();
        dynamicFiltersService.saveFilters(filters, "motherboard");
    }
    @PutMapping("/psu")
    public void updatePsuFilters() {
        List<Filter> filters = psuService.dynamicFilters();
        dynamicFiltersService.saveFilters(filters, "psu");
    }
    @PutMapping("/ram")
    public void updateRamFilters() {
        List<Filter> filters = ramService.dynamicFilters();
        dynamicFiltersService.saveFilters(filters, "ram");
    }
    @PutMapping("/fan")
    public void updateFanFilters() {
        List<Filter> filters = fanService.dynamicFilters();
        dynamicFiltersService.saveFilters(filters, "fan");
    }
}
