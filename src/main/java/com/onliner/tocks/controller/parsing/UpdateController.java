package com.onliner.tocks.controller.parsing;

import com.onliner.tocks.model.*;
import com.onliner.tocks.parsing.Parser;
import com.onliner.tocks.parsing.common.ProductsEnum;
import com.onliner.tocks.service.*;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/update")
public class UpdateController {

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

    public UpdateController(CpuService cpuService, GraphicCardService graphicCardService, FanService fanService, HDDService hddService, SSDService ssdService, PSUService psuService, MotherboardService motherboardService, RamService ramService, ChassisService chassisService, Parser parser) {
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
    public void parsingPricesOfProcessors() {
        List<CPU> cpus = (List<CPU>) parser.updateParsing(cpuService.findAll(), ProductsEnum.PROCESSORS);
        cpuService.saveAll(cpus);
        cpus = (List<CPU>) parser.parseAdditionalInformation(cpuService.findAllByPositionsNotNull(),ProductsEnum.PROCESSORS,false);
        cpuService.saveAll(cpus);
    }
    @PutMapping("/gpu")
    public void parsingPricesOfGraphicCards() {
        List<GraphicCard> graphicCards = (List<GraphicCard>) parser.updateParsing(graphicCardService.findAll(), ProductsEnum.GRAPHIC_CARDS);
        graphicCardService.saveAll(graphicCards);
        graphicCards = (List<GraphicCard>) parser.parseAdditionalInformation(graphicCardService.findAllByPositionsNotNull(),ProductsEnum.GRAPHIC_CARDS,false);
        graphicCardService.saveAll(graphicCards);
    }
    @PutMapping("/ssd")
    public void parsingPricesOfSSD() {
        List<SSD> ssds = (List<SSD>) parser.updateParsing(ssdService.findAll(), ProductsEnum.SSD);
        ssdService.saveAll(ssds);
        ssds = (List<SSD>) parser.parseAdditionalInformation(ssdService.findAllByPositionsNotNull(),ProductsEnum.SSD,false);
        ssdService.saveAll(ssds);
    }
    @PutMapping("/hdd")
    public void parsingPricesOfHDD() {
        List<HDD> hdds = (List<HDD>) parser.updateParsing(hddService.findAll(), ProductsEnum.HDD);
        hddService.saveAll(hdds);
        hdds = (List<HDD>) parser.parseAdditionalInformation(hddService.findAllByPositionsNotNull(),ProductsEnum.HDD,false);
        hddService.saveAll(hdds);
    }
    @PutMapping("/motherboard")
    public void parsingPricesOfMotherboard() {
        List<Motherboard> motherboards = (List<Motherboard>) parser.updateParsing(motherboardService.findAll(), ProductsEnum.MOTHERBOARDS);
        motherboardService.saveAll(motherboards);
        motherboards = (List<Motherboard>) parser.parseAdditionalInformation(motherboardService.findAllByPositionsNotNull(),ProductsEnum.MOTHERBOARDS,false);
        motherboardService.saveAll(motherboards);
    }
    @PutMapping("/psu")
    public void parsingPricesOfPSU() {
        List<PSU> psus = (List<PSU>) parser.updateParsing(psuService.findAll(), ProductsEnum.PSU);
        psuService.saveAll(psus);
        psus = (List<PSU>) parser.parseAdditionalInformation(psuService.findAllByPositionsNotNull(),ProductsEnum.PSU,false);
        psuService.saveAll(psus);
    }
    @PutMapping("/ram")
    public void parsingPricesOfRam() {
        List<Ram> rams = (List<Ram>) parser.updateParsing(ramService.findAllByPositionsNotNull(), ProductsEnum.RAM);
        ramService.saveAll(rams);
        rams = (List<Ram>) parser.parseAdditionalInformation(ramService.findAllByPositionsNotNull(),ProductsEnum.RAM,false);
        ramService.saveAll(rams);
    }
    @PutMapping("/fan")
    public void parsingPricesOfFan() {
        List<Fan> fans = (List<Fan>) parser.updateParsing(fanService.findAllByPositionsNotNull(),ProductsEnum.COOLING);
        fanService.saveAll(fans);
        fans = (List<Fan>) parser.parseAdditionalInformation(fanService.findAllByPositionsNotNull(),ProductsEnum.COOLING,false);
        fanService.saveAll(fans);

    }
    @PutMapping("/chassis")
    public void parsingPricesOfChassis() {
        List<Chassis> chassis = (List<Chassis>) parser.updateParsing(chassisService.findAllByPositionsNotNull(),ProductsEnum.CHASSIS);
        chassisService.saveAll(chassis);
        chassis = (List<Chassis>) parser.parseAdditionalInformation(chassisService.findAllByPositionsNotNull(), ProductsEnum.CHASSIS,false);
        chassisService.saveAll(chassis);
    }
}
