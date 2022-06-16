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
@RequestMapping("/parsing/additional")
public class AdditionalParsingController
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
    public AdditionalParsingController(CpuService cpuService, GraphicCardService graphicCardService, FanService fanService, HDDService hddService, SSDService ssdService, PSUService psuService, MotherboardService motherboardService, RamService ramService, ChassisService chassisService, Parser parser)
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
    public void parsingAdditionalInformationOfProcessors() {
        List<CPU> cpus = (List<CPU>) parser.parseAdditionalInformation(cpuService.findAllByPositionsNotNull(),ProductsEnum.PROCESSORS, true);
        cpuService.saveAll(cpus);
    }
    @PutMapping("/gpu")
    public void parsingAdditionalInformationOfGraphicCards() {
        List<GraphicCard> graphicCards = (List<GraphicCard>) parser.parseAdditionalInformation(graphicCardService.findAllByPositionsNotNull(),ProductsEnum.GRAPHIC_CARDS, true);
        graphicCardService.saveAll(graphicCards);
    }
    @PutMapping("/ssd")
    public void parsingAdditionalInformationOfSSD() {
        List<SSD> ssds = (List<SSD>) parser.parseAdditionalInformation(ssdService.findAllByPositionsNotNull(),ProductsEnum.SSD, true);
        ssdService.saveAll(ssds);
    }
    @PutMapping("/hdd")
    public void parsingAdditionalInformationOfHDD() {
        List<HDD> hdds = (List<HDD>) parser.parseAdditionalInformation(hddService.findAllByPositionsNotNull(),ProductsEnum.HDD, true);
        hddService.saveAll(hdds);
    }
    @PutMapping("/motherboard")
    public void parsingAdditionalInformationOfMotherboard() {
        List<Motherboard> motherboards = (List<Motherboard>) parser.parseAdditionalInformation(motherboardService.findAllByPositionsNotNull(), ProductsEnum.MOTHERBOARDS, true);
        motherboardService.saveAll(motherboards);
    }
    @PutMapping("/psu")
    public void parsingAdditionalInformationOfPSU() {
        List<PSU> psus = (List<PSU>) parser.parseAdditionalInformation(psuService.findAllByPositionsNotNull(),ProductsEnum.PSU, true);
        psuService.saveAll(psus);
    }
    @PutMapping("/ram")
    public void parsingAdditionalInformationOfRam() {
        List<Ram> rams = (List<Ram>) parser.parseAdditionalInformation(ramService.findAllByPositionsNotNull(),ProductsEnum.RAM, true);
        ramService.saveAll(rams);
    }
    @PutMapping("/fan")
    public void parsingAdditionalInformationForFan() {
        List<Fan> fans = (List<Fan>) parser.parseAdditionalInformation(fanService.findAllByPositionsNotNull(),ProductsEnum.COOLING, true);
        fanService.saveAll(fans);
    }
    @PutMapping("/chassis")
    public void parsingAdditionalInformationOfChassis() {
        List<Chassis> chassis = (List<Chassis>) parser.parseAdditionalInformation(chassisService.findAllByPositionsNotNull(),ProductsEnum.CHASSIS, true);
        chassisService.saveAll(chassis);
    }
}
