package com.onliner.tocks.controller;

import com.onliner.tocks.model.product.*;
import com.onliner.tocks.parsing.Parser;
import com.onliner.tocks.parsing.common.ProductsEnum;
import com.onliner.tocks.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/parsing/full")
public class FullParsingController
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
    private final Parser parser;

    @Autowired
    public FullParsingController(CpuService cpuService, GraphicCardService graphicCardService, FanService fanService, HDDService hddService, SSDService ssdService, PSUService psuService, MotherboardService motherboardService, RamService ramService, ChassisService chassisService, Parser parser)
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
        this.parser = parser;
    }
    @PutMapping("/cpu")
    public void fullParsingOfProcessors(){
        List<CPU> cpus = (List<CPU>) parser.fullInformationParsing(ProductsEnum.PROCESSORS);
        cpuService.saveAll(cpus);
    }
    @PutMapping("/gpu")
    public void fullParsingOfGraphicCards() {
        List<GraphicCard> graphicCards = (List<GraphicCard>) parser.fullInformationParsing(ProductsEnum.GRAPHIC_CARDS);
        graphicCardService.saveAll(graphicCards);
    }
    @PutMapping("/ssd")
    public void fullParsingOfSSD(){
        List<SSD> ssds = (List<SSD>) parser.fullInformationParsing(ProductsEnum.SSD);
        ssdService.saveAll(ssds);
    }
    @PutMapping("/hdd")
    public void fullParsingOfHDD(){
        List<HDD> hdds = (List<HDD>) parser.fullInformationParsing(ProductsEnum.HDD);
        hddService.saveAll(hdds);
    }
    @PutMapping("/motherboard")
    public void fullParsingOfMotherboard(){
        List<Motherboard> motherboards = (List<Motherboard>) parser.fullInformationParsing(ProductsEnum.MOTHERBOARDS);
        motherboardService.saveAll(motherboards);
    }
    @PutMapping("/psu")
    public void fullParsingOfPSU(){
        List<PSU> psus = (List<PSU>) parser.fullInformationParsing(ProductsEnum.PSU);
        psuService.saveAll(psus);
    }
    @PutMapping("/ram")
    public void fullParsingOfRam(){
        List<Ram> rams = (List<Ram>) parser.fullInformationParsing(ProductsEnum.RAM);
        ramService.saveAll(rams);
    }
    @PutMapping("/fan")
    public void fullParsingOfFan() {
        List<Fan> fans = (List<Fan>) parser.fullInformationParsing(ProductsEnum.COOLING);
        fanService.saveAll(fans);
    }
    @PutMapping("/chassis")
    public void fullParsingOfChassis() {
        List<Chassis> chassis = (List<Chassis>) parser.fullInformationParsing(ProductsEnum.CHASSIS);
        chassisService.saveAll(chassis);
    }
}
