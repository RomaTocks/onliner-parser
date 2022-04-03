package com.onliner.tocks.scheduler;

import com.onliner.tocks.controller.parsing.AdditionalParsingController;
import com.onliner.tocks.controller.parsing.MainParsingController;
import com.onliner.tocks.controller.parsing.PriceParsingController;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class Scheduler
{
    private final PriceParsingController priceParsingController;
    private final AdditionalParsingController additionalParsingController;
    private final MainParsingController mainParsingController;


    public Scheduler(PriceParsingController priceParsingController, AdditionalParsingController additionalParsingController, MainParsingController mainParsingController)
    {
        this.priceParsingController = priceParsingController;
        this.additionalParsingController = additionalParsingController;
        this.mainParsingController = mainParsingController;
    }

    @Scheduled(fixedDelay = 90, timeUnit = TimeUnit.MINUTES)
    public void scheduledParsingOfTDP(){
        mainParsingController.getTDPs();
    }
    @Scheduled(cron = "* 0 0 * * ?")
    public void scheduledParsingOfCPU() {
        priceParsingController.parsingPricesOfProcessors();
        additionalParsingController.parsingAdditionalInformationOfProcessors();
    }
    @Scheduled(cron = "* 30 2 * * ?")
    public void scheduledParsingPricesOfGPU() {
        priceParsingController.parsingPricesOfGraphicCards();
        additionalParsingController.parsingAdditionalInformationOfGraphicCards();
    }
    @Scheduled(cron = "* 0 5 * * ?")
    public void scheduledParsingOfFan() {
        priceParsingController.parsingPricesOfFan();
        additionalParsingController.parsingAdditionalInformationForFan();
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
    }
    @Scheduled(cron = "* 0 15 * * ?")
    public void scheduledParsingOfRam() {
        priceParsingController.parsingPricesOfRam();
        additionalParsingController.parsingAdditionalInformationOfRam();
    }
    @Scheduled(cron = "* 0 19 * * ?")
    public void scheduledParsingOfPSU() {
        priceParsingController.parsingPricesOfPSU();
        additionalParsingController.parsingAdditionalInformationOfPSU();
    }
    @Scheduled(cron = "* 30 22 * * ?")
    public void scheduledParsingOfChassis() {
        priceParsingController.parsingPricesOfChassis();
        additionalParsingController.parsingAdditionalInformationOfChassis();
    }
}
