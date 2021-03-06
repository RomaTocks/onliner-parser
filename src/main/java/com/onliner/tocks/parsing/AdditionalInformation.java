package com.onliner.tocks.parsing;

import com.onliner.tocks.model.product.*;
import com.onliner.tocks.model.product.additional.*;
import com.onliner.tocks.model.product.additional.values.GraphicCardAdditionalValues;
import com.onliner.tocks.parsing.common.ProductsEnum;
import com.onliner.tocks.parsing.common.tdp.TDP;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
@Slf4j
public class AdditionalInformation {

    public static Product setAdditionalInformationForProductFromMap(HashMap<String,String> map, Product product) {
        if(product instanceof CPU) {
            ((CPU) product).setAdditional(new CPUAdditionalInformation());
            map.forEach((infoKey, infoValue) -> ((CPU) product).setAdditional(setAdditionalInformationForProcessors(infoKey,infoValue,((CPU) product).getAdditional())));
        }
        if(product instanceof GraphicCard)
        {
            ((GraphicCard) product).setAdditional(new GraphicCardAdditionalInformation());
            map.forEach((infoKey, infoValue) -> ((GraphicCard) product).setAdditional(setAdditionalInformationForGraphicCard(infoKey, infoValue, ((GraphicCard) product).getAdditional())));
        }
        if(product instanceof Fan)
        {
            ((Fan) product).setAdditional(new FanAdditionalInformation());
            map.forEach((infoKey, infoValue) -> ((Fan) product).setAdditional(setAdditionalInformationForFan(infoKey, infoValue, ((Fan) product).getAdditional())));
        }
        if(product instanceof HDD)
        {
            ((HDD) product).setAdditional(new HDDAdditionalInformation());
            map.forEach((infoKey, infoValue) -> ((HDD) product).setAdditional(setAdditionalInformationForHDD(infoKey, infoValue, ((HDD) product).getAdditional())));
        }
        if(product instanceof SSD)
        {
            ((SSD) product).setAdditional(new SSDAdditionalInformation());
            map.forEach((infoKey, infoValue) -> ((SSD) product).setAdditional(setAdditionalInformationForSSD(infoKey, infoValue, ((SSD) product).getAdditional())));
        }
        if(product instanceof PSU)
        {
            ((PSU) product).setAdditional(new PSUAdditionalInformation());
            map.forEach((infoKey, infoValue) -> ((PSU) product).setAdditional(setAdditionalInformationForPSU(infoKey, infoValue, ((PSU) product).getAdditional())));
        }
        if(product instanceof Ram)
        {
            ((Ram) product).setAdditional(new RamAdditionalInformation());
            map.forEach((infoKey, infoValue) -> ((Ram) product).setAdditional(setAdditionalInformationForRam(infoKey, infoValue, ((Ram) product).getAdditional())));
        }
        if(product instanceof Motherboard)
        {
            ((Motherboard) product).setAdditional(new MotherboardAdditionalInformation());
            map.forEach((infoKey, infoValue) -> ((Motherboard) product).setAdditional(setAdditionalInformationForMotherboard(infoKey, infoValue, ((Motherboard) product).getAdditional())));
        }
        if(product instanceof Chassis)
        {
            ((Chassis) product).setAdditional(new ChassisAdditionalInformation());
            map.forEach((infoKey, infoValue) -> ((Chassis) product).setAdditional(setAdditionalInformationForChassis(infoKey, infoValue, ((Chassis) product).getAdditional())));
        }
        return product;
    }
    public static boolean isAdditionalInformationByEnum(Product product, ProductsEnum productsEnum) {
        boolean answer = true;
        switch (productsEnum) {
            case PROCESSORS : answer = ((CPU) product).getAdditional() == null;break;
            case GRAPHIC_CARDS : answer = ((GraphicCard) product).getAdditional() == null;break;
            case RAM : answer = ((Ram) product).getAdditional() == null;break;
            case SSD : answer = ((SSD) product).getAdditional() == null;break;
            case HDD : answer = ((HDD) product).getAdditional() == null;break;
            case COOLING : answer = ((Fan) product).getAdditional() == null;break;
            case PSU : answer = ((PSU) product).getAdditional() == null;break;
            case MOTHERBOARDS : answer = ((Motherboard) product).getAdditional() == null;break;
            case CHASSIS: answer = ((Chassis) product).getAdditional() == null;break;
        }
        return answer;
    }

    // TODO: 20.11.2021 ???????????????????? ?????????? ???????????????? ???????????????????????????? ????????????????????.
    public static Product getAdditionalInformationOfProduct(Product productIn, Product productOut) {
        if(productIn instanceof CPU) {
            ((CPU) productIn).setAdditional(((CPU)productOut).getAdditional());
        }
        if (productIn instanceof GraphicCard) {
            ((GraphicCard) productIn).setAdditional(((GraphicCard)productOut).getAdditional());
        }
        if (productIn instanceof Ram) {
            ((Ram) productIn).setAdditional(((Ram)productOut).getAdditional());
        }
        if (productIn instanceof Chassis) {
            ((Chassis) productIn).setAdditional(((Chassis)productOut).getAdditional());
        }
        if (productIn instanceof Motherboard) {
            ((Motherboard) productIn).setAdditional(((Motherboard)productOut).getAdditional());
        }
        if (productIn instanceof HDD) {
            ((HDD) productIn).setAdditional(((HDD)productOut).getAdditional());
        }
        if (productIn instanceof SSD) {
            ((SSD) productIn).setAdditional(((SSD)productOut).getAdditional());
        }
        if (productIn instanceof PSU) {
            ((PSU) productIn).setAdditional(((PSU)productOut).getAdditional());
        }
        if (productIn instanceof Fan) {
            ((Fan) productIn).setAdditional(((Fan)productOut).getAdditional());
        }
        return productIn;
    }

    // TODO: 20.11.2021 ???????????????????? ???????????????????? ?? GPU.
    public static GraphicCardAdditionalInformation setAdditionalInformationForGraphicCard(String key, String value, GraphicCardAdditionalInformation graphicCard) {
        switch (key)
        {
            case "???????? ???????????? ???? ??????????" : graphicCard.setBirthday(value);break;
            case "??????????????????" : graphicCard.setGpuInterface(value);break;
            case "?????????????????????????? ???????????????????????? ????????????????????" : graphicCard.setManufacturer(value);break;
            case "?????????????????????? ??????????????????" : graphicCard.setGpu(value);break;
            case "?????????????????????????? ????????????" : graphicCard.setBoost(value);break;
            case "???????????? ???? ???????????????? (LHR)" : graphicCard.setLhr(value);break;
            case "?????????????? (??????????????????????) ?????????????? ???????????????????????? ????????????????????" : graphicCard.setBaseFrequency(value);break;
            case "???????????????????????? ?????????????? ???????????????????????? ????????????????????" : graphicCard.setTurboFrequency(value);break;
            case "???????????????????? ?????????????????? ??????????????????????" : graphicCard.setStreamCores(value);break;
            case "?????????? ????????????????????" : graphicCard.setLength(value);break;
            case "???????????? ????????????????????" : graphicCard.setHeight(value);break;
            case "?????????????????????????? ???????? ??????????????" : graphicCard.setPSU(value);break;
            case "?????????????????????? ?????????????? ????????????" : graphicCard.setMemoryFrequency(value);break;
            case "???????????????????? ?????????????????????? ????????????" : graphicCard.setMemoryBandwidth(value);break;
            case "???????????? ???????? ????????????" : graphicCard.setMemoryBusWidth(value);break;
            case "?????????????????? DirectX" : graphicCard.setDirectx(value);break;
            case "?????????????????? SLI/CrossFire" : graphicCard.setSliCrossFire(value);break;
            case "?????????????? ??????????????" : graphicCard.setPowerConnectors(value);break;
            case "????????????????????" : graphicCard.setCooling(value);break;
            case "???????????????????? ????????????????????????" : graphicCard.setFanCount(value);break;
            case "HDMI" : graphicCard.setHdmi(value);break;
            case "DisplayPort" : graphicCard.setDisplayPort(value);break;
            case "mini HDMI" : graphicCard.setMiniHdmi(value);break;
        }
        return graphicCard;
    }

    // TODO: 20.11.2021 ???????????????????? ???????????????????? ?? CPU.
    public static CPUAdditionalInformation setAdditionalInformationForProcessors(String key, String value, CPUAdditionalInformation cpu) {
        switch (key)
        {
            case "?????????????????? ??????" : cpu.setCpuModel(value);break;
            case "?????? ????????????????" : cpu.setCpuPackage(value);break;
            case "??????????" : cpu.setSocket(value);break;
            case "???????? ???????????? ???? ??????????" : cpu.setBirthday(value);break;
            case "???????????????????? ?? ??????????????????" : cpu.setStockCooling(value);break;
            case "???????????????????? ????????" : cpu.setCores(value);break;
            case "???????????????????????? ???????????????????? ??????????????" : cpu.setStreams(value);break;
            case "?????????????? ???????????????? ??????????????" : cpu.setBaseFrequency(value);break;
            case "???????????????????????? ??????????????" : cpu.setTurboFrequency(value);break;
            case "?????????????????? ???????????????? ???????????????? (TDP)" : cpu.setTdp(value);break;
            case "?????????????? ???????????????? ??????????????????" : cpu.setCodename(value);break;
            case "?????????????????? ????????????" : cpu.setCpuRam(value);break;
            case "???????????????????? ?????????????? ????????????" : cpu.setCpuRamChannel(value);break;
            case "????????. ?????????????? ????????????" : cpu.setRamFrequency(value);break;
            case "???????????????????? ???????????????????? PCI Express" : cpu.setCpuPciExpress(value);break;
            case "???????????????????? ??????????????" : cpu.setCpuGraphics(value);break;
            case "?????? L2" : cpu.setL2cache(value);break;
            case "?????? L3" : cpu.setL3cache(value);break;
            case "????????????????????" : cpu.setTechProcess(value);break;
            case "?????????????????????????????? ????????" : cpu.setMultiThread(value);break;
        }
        return cpu;
    }

    // TODO: 20.11.2021 ???????????????????? ???????????????????? ?? ????????????????????????.
    public static FanAdditionalInformation setAdditionalInformationForFan(String key, String value, FanAdditionalInformation fan) {
        switch (key)
        {
            case "????????????????????" : fan.setCoolingType(value);break;
            case "????????" : fan.setColor(value);break;
            case "??????????" : fan.setSocket(value);break;
            case "???????????????????????? ????????????????" : fan.setDispelPower(value);break;
            case "???????????????? ????????????" : fan.setHeatPipes(value);break;
            case "?????????????? ??????????????????????" : fan.setDiameterFan(value);break;
            case "???????????????????????? ???????????????? ????????????????" : fan.setMaxSpeed(value);break;
            case "???????????????? ??????????????????" : fan.setMaterial(value);break;
            case "?????????????????????????? ????????????" : fan.setEvaporation(value);break;
            case "???????????????????? ????????????????????????" : fan.setFanCount(value);break;
            case "??????????????????" : fan.setBearing(value);break;
            case "???????????????????????? ?????????????????? ??????????" : fan.setFlow(value);break;
            case "???????????????? ???????????????? ???????????????? (PWM)" : fan.setPWM(value);break;
            case "??????????????????????????" : fan.setThermalControl(value);break;
            case "?????? ??????????????????????" : fan.setConnectType(value);break;
            case "LED-??????????????????" : fan.setLED(value);break;
            case "??????????????????????????" : fan.setVibration(value);break;
            case "???????????????????????? ?????????????? ????????" : fan.setNoise(value);break;
            case "????????????" : fan.setWidth(value);break;
            case "??????????????" : fan.setDepth(value);break;
            case "???????????? (??????????????)" : fan.setHeight(value);break;
            case "??????" : fan.setWeight(value);break;
        }
        return fan;
    }

    // TODO: 20.11.2021 ???????????????????? ???????????????????? ?? SSD.
    public static SSDAdditionalInformation setAdditionalInformationForSSD(String key, String value, SSDAdditionalInformation ssd) {
        switch (key)
        {
            case "???????? ???????????? ???? ??????????" : ssd.setBirthday(value);break;
            case "?????????????? 3.5\"" : ssd.setAdapter(value);break;
            case "???????????????? ?????????????????????????????????? ????????????" : ssd.setReadSpeed(value);break;
            case "???????????????? ???????????????????????????????? ????????????" : ssd.setWriteSpeed(value);break;
            case "?????????? ?????????????????? ???? ?????????? (????BF)" : ssd.setWorkTime(value);break;
            case "????????????????????" : ssd.setController(value);break;
            case "??????????????????" : ssd.setSsdInterface(value);break;
            case "?????? ?????????????????? Flash" : ssd.setMicroType(value);break;
            case "????????-????????????" : ssd.setFormFactor(value);break;
            case "??????????" : ssd.setVolume(value);break;
            case "?????????????? ?????????????????? M.2" : ssd.setM2Size(value);break;
            case "???????????? ????????????" : ssd.setRecordResource(value);break;
            case "???????????????????? ????????????????????" : ssd.setEncryption(value);break;
            case "????????????????????" : ssd.setCooling(value);break;
            case "??????????????????" : ssd.setBacklight(value);break;
        }
        return ssd;
    }

    // TODO: 20.11.2021 ???????????????????? ???????????????????? ?? HDD.
    public static HDDAdditionalInformation setAdditionalInformationForHDD(String key, String value, HDDAdditionalInformation hdd) {
        switch (key)
        {
            case "???????????????? ?????????????????????????????????? ????????????" : hdd.setReadSpeed(value);break;
            case "???????????????? ???????????????????????????????? ????????????" : hdd.setWriteSpeed(value);break;
            case "????????-????????????" : hdd.setFormFactor(value);break;
            case "???????????????? ???????????????? ????????????????" : hdd.setSpindleSpeed(value);break;
            case "??????????" : hdd.setBuffer(value);break;
            case "??????????" : hdd.setVolume(value);break;
            case "??????????????????" : hdd.setHddInterface(value);break;
            case "???????????????????? ????????????????????" : hdd.setEncryption(value);break;
            case "???????????? ??????????????" : hdd.setSectorSize(value);break;
            case "?????????????? ???????? ?????? ????????????/????????????" : hdd.setReadOrWriteNoise(value);break;
            case "?????????????? ???????? ?? ???????????? ????????????????" : hdd.setWaitingNoise(value);break;
            case "?????????????????????????????????? (????????????/????????????)" : hdd.setPowerUsage(value);break;
            case "??????????????" : hdd.setDepth(value);break;
        }
        return hdd;
    }

    // TODO: 20.11.2021 ???????????????????? ???????????????????? ?? PSU.
    public static PSUAdditionalInformation setAdditionalInformationForPSU(String key, String value, PSUAdditionalInformation psu) {
        switch (key)
        {
            case "????????????????" : psu.setPower(value);break;
            case "???????????????? ?????????? ??????????????" : psu.setStandard(value);break;
            case "??????" : psu.setEfficiency(value);break;
            case "???????????????????? 80 PLUS" : psu.setCertificate(value);break;
            case "???????????? ?????????????????????? ?????????? ??????????????" : psu.setFan(value);break;
            case "CPU 4 pin" : psu.setCpu4pin(value);break;
            case "PCIe 6 pin" : psu.setPcie6pin(value);break;
            case "PCIe 8 pin" : psu.setPcie8pin(value);break;
            case "????????????" : psu.setHeight(value);break;
            case "????????????" : psu.setWidth(value);break;
            case "??????????????" : psu.setDepth(value);break;
            case "IDE 4 pin" : psu.setIde4pin(value);break;
            case "???????????????? ???????????????? ???????????????????? ????????" : psu.setRange(value);break;
        }
        return psu;
    }

    // TODO: 20.11.2021 ???????????????????? ???????????????????? ?? ?????????????????????? ????????????.
    public static MotherboardAdditionalInformation setAdditionalInformationForMotherboard(String key, String value, MotherboardAdditionalInformation motherboard) {
        switch (key)
        {
            case "???????? ???????????? ???? ??????????" : motherboard.setBirthday(value);break;
            case "??????????" : motherboard.setSocket(value);break;
            case "????????-????????????" : motherboard.setFormFactor(value);break;
            case "???????????????????????? ?????????? ????????????" : motherboard.setMaxMemory(value);break;
            case "?????????? ????????????" : motherboard.setMemoryMode(value);break;
            case "???????????????????????? ?????????????? ????????????" : motherboard.setMaxMemoryFrequency(value);break;
            case "M.2" : motherboard.setM2(value);break;
            case "SATA 3.0" : motherboard.setSata3(value);break;
            case "?????????????????? ??????????????????????" : motherboard.setCpuSupport(value);break;
            case "????????????" : motherboard.setChipset(value);break;
            case "??????????????????" : motherboard.setBacklight(value);break;
            case "?????? ????????????" : motherboard.setMemoryType(value);break;
            case "???????????????????? ???????????? ????????????" : motherboard.setMemorySlots(value);break;
            case "??????????" : motherboard.setLength(value);break;
            case "????????????" : motherboard.setWidth(value);break;
            case "Wi-Fi" : motherboard.setWifi(value);break;
            case "?????????????????? ???????????????????? ??????????????" : motherboard.setIntegratedGraphics(value);break;
            case "?????????????????? SLi/CrossFire" : motherboard.setSli(value);break;
            case "HDMI" : motherboard.setHdmi(value);break;
        }
        return motherboard;
    }

    // TODO: 20.11.2021 ???????????????????? ???????????????????? ?? ????????????.
    public static RamAdditionalInformation setAdditionalInformationForRam(String key, String value, RamAdditionalInformation ram) {
        switch (key)
        {
            case "??????????" : ram.setKit(value);break;
            case "?????????? ??????????" : ram.setValue(value);break;
            case "?????????? ???????????? ????????????" : ram.setSingleValue(value);
            case "??????" : ram.setType(value);break;
            case "??????????????" : ram.setFrequency(value);break;
            case "????????????????" : ram.setTiming(value);break;
            case "?????????????? XMP" : ram.setXmp(value);break;
        }
        return ram;
    }

    // TODO: 28.11.2021 ???????????????????? ???????????????????? ?? ????????????????.
    public static ChassisAdditionalInformation setAdditionalInformationForChassis(String key, String value, ChassisAdditionalInformation chassis) {
        switch (key)
        {
            case "???????? ??????????????" : chassis.setPsuKit(value);break;
            case "?????? ??????????????" : chassis.setType(value);break;
            case "???????? ??????????????" : chassis.setColor(value);break;
            case "???????????????? ????????" : chassis.setWindowMaterial(value);break;
            case "????????. ???????????? ?????????????????????? ??????????" : chassis.setMaxSizeOfMotherboard(value);break;
            case "?????????????????????? ?????????????????????? ??????????" : chassis.setMotherboardsCompatibleSizes(value);break;
            case "???????????????????????? ?????????? ??????????????" : chassis.setPsuLocation(value);break;
            case "?????????????????? ?????????????????????? ????????????????????" : chassis.setWaterCoolingSupport(value);break;
            case "???????????????????? ???????? ?????? ????????????????????????" : chassis.setFanSection(value);break;
            case "?????????????????????????? ??????????????????????" : chassis.setFanKit(value);break;
            case "????????. ?????????? ????????????????????" : chassis.setMaxGPULength(value);break;
            case "????????. ???????????? ?????????????????????????? ????????????" : chassis.setMaxCPUCoolingSystemHeight(value);break;
            case "????????. ?????????? ?????????? ??????????????" : chassis.setMaxPSULength(value);break;
            case "????????????" : chassis.setHeight(value);break;
            case "????????????" : chassis.setWidth(value);break;
            case "??????????????" : chassis.setDepth(value);break;
            case "??????" : chassis.setWeight(value);break;
        }
        return chassis;
    }
    public static List<GraphicCard> setGraphicCardsTDP(List<GraphicCard> graphicCards, List<TDP> tdpList) {
        log.info("Setting TDP to graphic cards.");
        log.info("Remove nullable additional from target list...");
        graphicCards.removeIf(graphicCard -> graphicCard.getAdditional() == null || graphicCard.getValues() == null);
        log.info("Remove graphic cards with TDP from target list...");
        graphicCards.removeIf(graphicCard -> graphicCard.getAdditional().getTdp() != null && graphicCard.getValues().getTdp() != null);
        graphicCards.forEach(graphicCard -> tdpList.stream()
           .filter(tdp -> tdp.getName().equals(graphicCard.getAdditional().getGpu()))
           .findFirst()
           .ifPresent(tdp -> {
                GraphicCardAdditionalInformation graphicCardAdditionalInformation = graphicCard.getAdditional();
                GraphicCardAdditionalValues graphicCardAdditionalValues = graphicCard.getValues();
                graphicCardAdditionalInformation.setTdp(tdp.getTdp());
                graphicCardAdditionalValues.setTdp(tdp.getTdpValue());
                graphicCard.setAdditional(graphicCardAdditionalInformation);
                graphicCard.setValues(graphicCardAdditionalValues);
           })
        );
        log.info("Setting TDP ended.");
        return graphicCards;
    }
}
