package com.onliner.tocks.transform;

import com.onliner.tocks.model.*;
import com.onliner.tocks.model.additional.*;
import com.onliner.tocks.model.additional.values.*;
import com.onliner.tocks.parsing.common.ProductsEnum;
import com.onliner.tocks.parsing.common.product.Product;
import com.onliner.tocks.parsing.converter.Converter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

@Component
@Slf4j
public class Transform
{
    public static LinkedHashMap<String, String> listsToHashMap(List<String> keys, List<String> values) {
        keys.removeIf(s -> s.trim().isEmpty());
        LinkedHashMap<String, String> additionalInformation = new LinkedHashMap<>();
        Iterator<String> keysIterator = keys.iterator();
        Iterator<String> valueIterator = values.iterator();
        while (keysIterator.hasNext() && valueIterator.hasNext()) {
            additionalInformation.put(keysIterator.next(),valueIterator.next());
        }
        return additionalInformation;
    }
    public static List<? extends Product> transformList(List<? extends Product> list, ProductsEnum productsEnum) {
        switch (productsEnum) {
            case PROCESSORS: {
                List<CPU> cpus = new ArrayList<>();
                list.forEach(product -> {
                    CPU cpu = new CPU();
                    cpu.setInformation(product);
                    cpus.add(cpu);
                });
                return cpus;
            }
            case GRAPHIC_CARDS: {
                List<GraphicCard> graphicCards = new ArrayList<>();
                list.forEach(product -> {
                    GraphicCard graphicCard = new GraphicCard();
                    graphicCard.setInformation(product);
                    graphicCards.add(graphicCard);
                });
                return graphicCards;
            }
            case RAM: {
                List<Ram> rams = new ArrayList<>();
                list.forEach(product -> {
                    Ram ram = new Ram();
                    ram.setInformation(product);
                    rams.add(ram);
                });
                return rams;
            }
            case SSD: {
                List<SSD> ssds = new ArrayList<>();
                list.forEach(product -> {
                    SSD ssd = new SSD();
                    ssd.setInformation(product);
                    ssds.add(ssd);
                });
                return ssds;
            }
            case HDD: {
                List<HDD> hdds = new ArrayList<>();
                list.forEach(product -> {
                    HDD hdd = new HDD();
                    hdd.setInformation(product);
                    hdds.add(hdd);
                });
                return hdds;
            }
            case COOLING: {
                List<Fan> fans = new ArrayList<>();
                list.forEach(product -> {
                    Fan fan = new Fan();
                    fan.setInformation(product);
                    fans.add(fan);
                });
                return fans;
            }
            case PSU: {
                List<PSU> psus = new ArrayList<>();
                list.forEach(product -> {
                    PSU psu = new PSU();
                    psu.setInformation(product);
                    psus.add(psu);
                });
                return psus;
            }
            case MOTHERBOARDS: {
                List<Motherboard> motherboards = new ArrayList<>();
                list.forEach(product -> {
                    Motherboard motherboard = new Motherboard();
                    motherboard.setInformation(product);
                    motherboards.add(motherboard);
                });
                return motherboards;
            }
            case CHASSIS: {
                List<Chassis> chassises = new ArrayList<>();
                list.forEach(product -> {
                    Chassis chassis = new Chassis();
                    chassis.setInformation(product);
                    chassises.add(chassis);
                });
                return chassises;
            }
            default:{
                return null;
            }
        }
    }
    public static List<? extends Product> getValuesFromAdditional(List<? extends Product> list, ProductsEnum productsEnum) {
        switch (productsEnum) {

            case PROCESSORS: {
                List<CPU> cpus = (List<CPU>) list;
                cpus.forEach(cpu -> {
                    CPUAdditionalValues cpuAdditionalValues = new CPUAdditionalValues();
                    CPUAdditionalInformation additional = cpu.getAdditional();
                    cpuAdditionalValues.setBirthday(checkStringInteger(additional.getBirthday()));
                    cpuAdditionalValues.setCores(checkStringInteger(additional.getCores()));
                    cpuAdditionalValues.setCpuRamChannel(checkStringInteger(additional.getCpuRamChannel()));
                    cpuAdditionalValues.setStreams(checkStringInteger(additional.getStreams()));
                    cpuAdditionalValues.setTdp(checkStringInteger(additional.getTdp()));
                    cpuAdditionalValues.setStreams(checkStringInteger(additional.getStreams()));
                    cpuAdditionalValues.setBaseFrequency(checkStringDouble(additional.getBaseFrequency()));
                    cpuAdditionalValues.setTurboFrequency(checkStringDouble(additional.getTurboFrequency()));
                    cpuAdditionalValues.setRamFrequency(checkStringInteger(additional.getRamFrequency()));
                    cpu.setValues(cpuAdditionalValues);
                });
                return cpus;
            }
            case GRAPHIC_CARDS:
                List<GraphicCard> graphicCards = (List<GraphicCard>) list;
                graphicCards.forEach(graphicCard -> {
                    GraphicCardAdditionalValues graphicCardAdditionalValues = new GraphicCardAdditionalValues();
                    GraphicCardAdditionalInformation additional = graphicCard.getAdditional();
                    graphicCardAdditionalValues.setHeight(checkStringDouble(additional.getHeight()));
                    graphicCardAdditionalValues.setLength(checkStringDouble(additional.getLength()));
                    graphicCardAdditionalValues.setVideoMemory(checkStringInteger(additional.getVideoMemory()));
                    graphicCardAdditionalValues.setPSU(checkStringInteger(additional.getPSU()));
                    graphicCard.setValues(graphicCardAdditionalValues);
                });
                return graphicCards;
            case RAM:
                List<Ram> rams = (List<Ram>) list;
                rams.forEach(ram -> {
                    RamAdditionalValues ramAdditionalValues = new RamAdditionalValues();
                    RamAdditionalInformation additional = ram.getAdditional();
                    ramAdditionalValues.setFrequency(checkStringInteger(additional.getFrequency()));
                    ramAdditionalValues.setKit(checkStringInteger(additional.getKit()));
                    ram.setValues(ramAdditionalValues);
                });
                return rams;
            case SSD:
                List<SSD> ssds = (List<SSD>) list;
                ssds.forEach(ssd -> {
                    SSDAdditionalValues ssdAdditionalValues = new SSDAdditionalValues();
                    SSDAdditionalInformation additional = ssd.getAdditional();
                    ssdAdditionalValues.setVolume(checkStringInteger(additional.getVolume()));
                    ssd.setValues(ssdAdditionalValues);
                });
                return ssds;
            case HDD:
                List<HDD> hdds = (List<HDD>) list;
                hdds.forEach(hdd -> {
                    HDDAdditionalValues hddAdditionalValues = new HDDAdditionalValues();
                    HDDAdditionalInformation additional = hdd.getAdditional();
                    hddAdditionalValues.setVolume(checkStringInteger(additional.getVolume()));
                    hddAdditionalValues.setDepth(checkStringInteger(additional.getDepth()));
                    hddAdditionalValues.setPowerUsage(checkStringDouble(additional.getPowerUsage()));
                    hdd.setValues(hddAdditionalValues);
                });
                return hdds;
            case COOLING:
                List<Fan> fans = (List<Fan>) list;
                fans.forEach(fan -> {
                    FanAdditionalValues fanAdditionalValues = new FanAdditionalValues();
                    FanAdditionalInformation additional = fan.getAdditional();
                    fanAdditionalValues.setDiameterFan(checkStringDouble(additional.getDiameterFan()));
                    fanAdditionalValues.setDispelPower(checkStringInteger(additional.getDispelPower()));
                    fanAdditionalValues.setHeight(checkStringDouble(additional.getHeight()));
                    fan.setValues(fanAdditionalValues);
                });
                return fans;
            case PSU:
                List<PSU> psus = (List<PSU>) list;
                psus.forEach(psu -> {
                    PSUAdditionalValues psuAdditionalValues = new PSUAdditionalValues();
                    PSUAdditionalInformation additional = psu.getAdditional();
                    psuAdditionalValues.setPower(checkStringInteger(additional.getPower()));
                    psuAdditionalValues.setHeight(checkStringDouble(additional.getHeight()));
                    psuAdditionalValues.setWidth(checkStringDouble(additional.getWidth()));
                    psu.setValues(psuAdditionalValues);
                });
                return psus;
            case MOTHERBOARDS:
                List<Motherboard> motherboards = (List<Motherboard>) list;
                motherboards.forEach(motherboard -> {
                    MotherboardAdditionalValues values = new MotherboardAdditionalValues();
                    MotherboardAdditionalInformation additional = motherboard.getAdditional();
                    values.setChassisFan(checkStringInteger(additional.getChassisFan()));
                    motherboard.setValues(values);

                });
                return motherboards;
            case CHASSIS:
                List<Chassis> chassises = (List<Chassis>) list;
                chassises.forEach(chassis -> {
                    ChassisAdditionalValues values = new ChassisAdditionalValues();
                    ChassisAdditionalInformation additional = chassis.getAdditional();
                    values.setDepth(checkStringDouble(additional.getDepth()));
                    values.setHeight(checkStringDouble(additional.getHeight()));
                    values.setFanKit(checkStringInteger(additional.getFanKit()));
                    values.setWeight(checkStringDouble(additional.getWeight()));
                    values.setMaxCPUCoolingSystemHeight(checkStringDouble(additional.getMaxCPUCoolingSystemHeight()));
                    values.setMaxGPULength(checkStringDouble(additional.getMaxGPULength()));
                    values.setMaxPSULength(checkStringDouble(additional.getMaxPSULength()));
                    chassis.setValues(values);
                });
                return chassises;
            default: {
                return null;
            }
        }
    }
    private static Integer checkStringInteger(String string) {
        return string != null ? Converter.getIntegersFromString(string) : null;
    }
    private static Double checkStringDouble(String string) {
        return string != null ? Converter.getDoubleFromString(string) : null;
    }
}
