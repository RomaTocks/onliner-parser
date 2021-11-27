package com.onliner.tocks.controller.parsing;

import com.onliner.tocks.model.*;
import com.onliner.tocks.parsing.Parser;
import com.onliner.tocks.parsing.ProductsEnum;
import com.onliner.tocks.parsing.common.Product;
import com.onliner.tocks.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static com.onliner.tocks.parsing.common.General.getChapterTimeout;

@RestController
@RequestMapping("/parsing")
@Slf4j
public class MainParsingController
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
    public MainParsingController(CpuService cpuService, GraphicCardService graphicCardService, FanService fanService, HDDService hddService, SSDService ssdService, PSUService psuService, MotherboardService motherboardService, RamService ramService, Parser parser)
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

    @PutMapping()
    public void updateParsingOfAccessories() {

        for (ProductsEnum productsEnum : ProductsEnum.values())
        {
            System.out.println();
            log.info("\u001B[45mParsing of " + productsEnum.name() + "\u001B[0m");
            System.out.println();
            List<? extends Product> list = parser.updateParsing(choiceGetService(productsEnum,false),productsEnum);
            choiceSavingService(list);
            getChapterTimeout();
            List<? extends Product> notNullPriceList = choiceGetService(productsEnum,true);
            List<? extends Product> listWithAdditional = parser.parseAdditionalInformation(notNullPriceList, productsEnum);
            choiceSavingService(listWithAdditional);
            getChapterTimeout();
        }
    }
    public List<? extends Product> choiceGetService(ProductsEnum productsEnum, boolean sellersNotNull) {
        List<? extends Product> list = new ArrayList<>();
        switch(productsEnum) {
            case PROCESSORS: list = sellersNotNull ? cpuService.findAllByPositionsNotNull() : cpuService.findAll();break;
            case GRAPHIC_CARDS: list = sellersNotNull ? graphicCardService.findAllByPositionsNotNull() : graphicCardService.findAll();break;
            case RAM: list = sellersNotNull ? ramService.findAllByPositionsNotNull() : ramService.findAll();break;
            case SSD: list = sellersNotNull ? ssdService.findAllByPositionsNotNull() : ssdService.findAll();break;
            case HDD: list = sellersNotNull ? hddService.findAllByPositionsNotNull() : hddService.findAll();break;
            case COOLING: list = sellersNotNull ? fanService.findAllByPositionsNotNull() : fanService.findAll();break;
            case PSU: list = sellersNotNull ? psuService.findAllByPositionsNotNull() : psuService.findAll();break;
            case MOTHERBOARDS: list = sellersNotNull ? motherboardService.findAllByPositionsNotNull() : motherboardService.findAll();break;
        }
        return  list;
    }
    public void choiceSavingService(List<? extends Product> list)
    {
        if(list.get(0) instanceof CPU) {
            cpuService.saveAll((List<CPU>) list);
        }
        if(list.get(0) instanceof GraphicCard) {
            graphicCardService.saveAll((List<GraphicCard>) list);
        }
        if(list.get(0) instanceof Fan) {
            fanService.saveAll((List<Fan>)list);
        }
        if(list.get(0) instanceof SSD) {
            ssdService.saveAll((List<SSD>)list);
        }
        if (list.get(0) instanceof HDD) {
            hddService.saveAll((List<HDD>)list);
        }
        if(list.get(0) instanceof Motherboard) {
            motherboardService.saveAll((List<Motherboard>)list);
        }
        if(list.get(0) instanceof PSU){
            psuService.saveAll((List<PSU>)list);
        }
        if(list.get(0) instanceof Ram) {
            ramService.saveAll((List<Ram>)list);
        }
    }
}