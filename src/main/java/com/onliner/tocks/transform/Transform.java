package com.onliner.tocks.transform;

import com.onliner.tocks.model.*;
import com.onliner.tocks.parsing.common.ProductsEnum;
import com.onliner.tocks.parsing.common.product.Product;
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
    public static Integer getNumbersInString(String string) {
        String numbers = string.replaceAll("[^0-9]", "");
        return Integer.parseInt(numbers);
    }
}
