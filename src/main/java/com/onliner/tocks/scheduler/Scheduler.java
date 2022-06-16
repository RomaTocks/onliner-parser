package com.onliner.tocks.scheduler;

import com.onliner.tocks.controller.AdditionalParsingController;
import com.onliner.tocks.controller.FilterController;
import com.onliner.tocks.controller.MainParsingController;
import com.onliner.tocks.controller.PriceParsingController;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class Scheduler
{
    private final PriceParsingController priceParsingController;
    private final AdditionalParsingController additionalParsingController;
    private final MainParsingController mainParsingController;
    private final FilterController filterController;


    public Scheduler(PriceParsingController priceParsingController, AdditionalParsingController additionalParsingController, MainParsingController mainParsingController, FilterController filterController)
    {
        this.priceParsingController = priceParsingController;
        this.additionalParsingController = additionalParsingController;
        this.mainParsingController = mainParsingController;
        this.filterController = filterController;
    }

    @Scheduled(fixedDelay = 90, timeUnit = TimeUnit.MINUTES)
    public void scheduledParsingOfTDP(){
        mainParsingController.getTDPs();
    }
    @Scheduled(cron = "* 0 0 * * ?")
    public void scheduledParsingOfCPU() {
        priceParsingController.parsingPricesOfProcessors();
        additionalParsingController.parsingAdditionalInformationOfProcessors();
        filterController.updateProcessorsFilters();

    }
    @Scheduled(cron = "* 30 2 * * ?")
    public void scheduledParsingPricesOfGPU() {
        priceParsingController.parsingPricesOfGraphicCards();
        additionalParsingController.parsingAdditionalInformationOfGraphicCards();
        filterController.updateGraphicCardFilters();
    }
    @Scheduled(cron = "* 0 5 * * ?")
    public void scheduledParsingOfFan() {
        priceParsingController.parsingPricesOfFan();
        additionalParsingController.parsingAdditionalInformationForFan();
        filterController.updateFanFilters();
    }
    @Scheduled(cron = "* 30 7 * * ?")
    public void scheduledParsingOfHDD() {
        priceParsingController.parsingPricesOfHDD();
        additionalParsingController.parsingAdditionalInformationOfHDD();
    }
    @Scheduled(cron = "* 0 10 * * ?")
    public void scheduledParsingOfSSD() {
        priceParsingController.parsingPricesOfSSD();
        additionalParsingController.parsingAdditionalInformationOfSSD();
    }
    @Scheduled(cron = "* 30 12 * * ?")
    public void scheduledParsingOfMotherboard() {
        priceParsingController.parsingPricesOfMotherboard();
        additionalParsingController.parsingAdditionalInformationOfMotherboard();
        filterController.updateMotherboardFilters();
    }
    @Scheduled(cron = "* 0 15 * * ?")
    public void scheduledParsingOfRam() {
        priceParsingController.parsingPricesOfRam();
        additionalParsingController.parsingAdditionalInformationOfRam();
        filterController.updateRamFilters();
    }
    @Scheduled(cron = "* 0 19 * * ?")
    public void scheduledParsingOfPSU() {
        priceParsingController.parsingPricesOfPSU();
        additionalParsingController.parsingAdditionalInformationOfPSU();
        filterController.updatePsuFilters();
    }
    @Scheduled(cron = "* 30 22 * * ?")
    public void scheduledParsingOfChassis() {
        priceParsingController.parsingPricesOfChassis();
        additionalParsingController.parsingAdditionalInformationOfChassis();
        filterController.updatePsuFilters();
    }
}
