package com.onliner.tocks.controller.parsing;

import com.onliner.tocks.model.*;
import com.onliner.tocks.parsing.Parser;
import com.onliner.tocks.parsing.ProductsEnum;
import com.onliner.tocks.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("parsing/price")
public class PriceParsingController
{
    private final CpuService cpuService;
    private final GraphicCardService graphicCardService;
    private final FanService fanService;
    private final HDDService hddService;
    private final SSDService ssdService;
    private final PSUService psuService;
    private final MotherboardService motherboardService;
    private final RamService ramService;
    private final Parser parser;

    @Autowired
    public PriceParsingController(CpuService cpuService, GraphicCardService graphicCardService, FanService fanService, HDDService hddService, SSDService ssdService, PSUService psuService, MotherboardService motherboardService, RamService ramService, Parser parser)
    {
        this.cpuService = cpuService;
        this.graphicCardService = graphicCardService;
        this.fanService = fanService;
        this.hddService = hddService;
        this.ssdService = ssdService;
        this.psuService = psuService;
        this.motherboardService = motherboardService;
        this.ramService = ramService;
        this.parser = parser;
    }
    @PutMapping("/cpu")
    public void parsingPricesOfProcessors() {
        List<CPU> cpus = (List<CPU>) parser.updateParsing(cpuService.findAll(), ProductsEnum.PROCESSORS);
        cpuService.saveAll(cpus);
    }
    @PutMapping("/gpu")
    public void parsingPricesOfGraphicCards() {
        List<GraphicCard> graphicCards = (List<GraphicCard>) parser.updateParsing(graphicCardService.findAll(), ProductsEnum.GRAPHIC_CARDS);
        graphicCardService.saveAll(graphicCards);
    }
    @PutMapping("/ssd")
    public void parsingPricesOfSSD() {
        List<SSD> ssds = (List<SSD>) parser.updateParsing(ssdService.findAll(), ProductsEnum.SSD);
        ssdService.saveAll(ssds);
    }
    @PutMapping("/hdd")
    public void parsingPricesOfHDD() {
        List<HDD> hdds = (List<HDD>) parser.updateParsing(hddService.findAll(), ProductsEnum.HDD);
        hddService.saveAll(hdds);
    }
    @PutMapping("/motherboard")
    public void parsingPricesOfMotherboard() {
        List<Motherboard> motherboards = (List<Motherboard>) parser.updateParsing(motherboardService.findAll(), ProductsEnum.MOTHERBOARDS);
        motherboardService.saveAll(motherboards);
    }
    @PutMapping("/psu")
    public void parsingPricesOfPSU() {
        List<PSU> psus = (List<PSU>) parser.updateParsing(psuService.findAll(), ProductsEnum.PSU);
        psuService.saveAll(psus);
    }
    @PutMapping("/ram")
    public void parsingPricesOfRam() {
        List<Ram> rams = (List<Ram>) parser.updateParsing(ramService.findAllByPositionsNotNull(), ProductsEnum.RAM);
        ramService.saveAll(rams);
    }
    @PutMapping("/fan")
    public void parsingPricesOfFan() {
        List<Fan> fans = (List<Fan>) parser.updateParsing(fanService.findAllByPositionsNotNull(),ProductsEnum.COOLING);
        fanService.saveAll(fans);
    }
}
