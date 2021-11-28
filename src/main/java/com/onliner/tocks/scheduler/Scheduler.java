package com.onliner.tocks.scheduler;

import com.onliner.tocks.controller.parsing.AdditionalParsingController;
import com.onliner.tocks.controller.parsing.PriceParsingController;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler
{
    private final PriceParsingController priceParsingController;
    private final AdditionalParsingController additionalParsingController;

    public Scheduler(PriceParsingController priceParsingController, AdditionalParsingController additionalParsingController)
    {
        this.priceParsingController = priceParsingController;
        this.additionalParsingController = additionalParsingController;
    }

    @Scheduled(cron = "* 0 23 * * ?")
    public void scheduledParsingOfCPU() {
        priceParsingController.parsingPricesOfProcessors();
        additionalParsingController.parsingAdditionalInformationOfProcessors();
    }
    @Scheduled(cron = "* 0 2 * * ?")
    public void scheduledParsingPricesOfGPU() {
        priceParsingController.parsingPricesOfGraphicCards();
        additionalParsingController.parsingAdditionalInformationOfGraphicCards();
    }
    @Scheduled(cron = "* 0 5 * * ?")
    public void scheduledParsingOfFan() {
        priceParsingController.parsingPricesOfFan();
        additionalParsingController.parsingAdditionalInformationForFan();
    }
    @Scheduled(cron = "* 0 8 * * ?")
    public void scheduledParsingOfHDD() {
        priceParsingController.parsingPricesOfHDD();
        additionalParsingController.parsingAdditionalInformationOfHDD();
    }
    @Scheduled(cron = "* 0 11 * * ?")
    public void scheduledParsingOfSSD() {
        priceParsingController.parsingPricesOfSSD();
        additionalParsingController.parsingAdditionalInformationOfSSD();
    }
    @Scheduled(cron = "* 0 14 * * ?")
    public void scheduledParsingOfMotherboard() {
        priceParsingController.parsingPricesOfMotherboard();
        additionalParsingController.parsingAdditionalInformationOfMotherboard();
    }
    @Scheduled(cron = "* 0 17 * * ?")
    public void scheduledParsingOfRam() {
        priceParsingController.parsingPricesOfRam();
        additionalParsingController.parsingAdditionalInformationOfRam();
    }
    @Scheduled(cron = "* 0 20 * * ?")
    public void scheduledParsingOfPSU() {
        priceParsingController.parsingPricesOfPSU();
        additionalParsingController.parsingAdditionalInformationOfPSU();
    }
}
