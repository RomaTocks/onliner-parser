package com.onliner.tocks.parsing.common;

import com.onliner.tocks.exception.RequestException;
import com.onliner.tocks.model.*;
import com.onliner.tocks.parsing.ProductsEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class General
{
    public static Product getObjectByEnum(ProductsEnum productsEnum) {
        Product product = null;
        switch (productsEnum) {
            case PROCESSORS : product = new CPU();break;
            case GRAPHIC_CARDS : product = new GraphicCard();break;
            case RAM : product = new Ram();break;
            case SSD : product = new SSD();break;
            case HDD : product = new HDD();break;
            case COOLING : product = new Fan();break;
            case PSU : product = new PSU();break;
            case MOTHERBOARDS : product = new Motherboard();break;
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
        }
        return answer;
    }
    public static String getUrlByEnum(ProductsEnum productsEnum) {
        String URL;
        switch (productsEnum) {
            case PROCESSORS : URL = "https://catalog.onliner.by/sdapi/catalog.api/search/cpu";break;
            case GRAPHIC_CARDS: URL = "https://catalog.onliner.by/sdapi/catalog.api/search/videocard";break;
            case RAM: URL = "https://catalog.onliner.by/sdapi/catalog.api/search/dram";break;
            case SSD: URL = "https://catalog.onliner.by/sdapi/catalog.api/search/ssd";break;
            case HDD: URL = "https://catalog.onliner.by/sdapi/catalog.api/search/hdd";break;
            case COOLING: URL = "https://catalog.onliner.by/sdapi/catalog.api/search/fan?" + "type_fan[0]=cpu";break;
            case PSU: URL = "https://catalog.onliner.by/sdapi/catalog.api/search/powersupply";break;
            case MOTHERBOARDS: URL = "https://catalog.onliner.by/sdapi/catalog.api/search/motherboard";break;
            default:
                throw new IllegalStateException("Unexpected value: " + productsEnum);
        }
        return URL;
    }
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
    public static void getTimeout() {
        int random = (int) (Math.random()*2) + 1;
        System.out.println();
        log.info("Try to avoid ban from onliner.by : Waiting " + random + " seconds.");
        System.out.println();
        try {
            TimeUnit.SECONDS.sleep(random);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void getChapterTimeout() {
        System.out.println();
        log.info("Try to avoid ban from onliner.by : Next chapter, waiting " + 1 + " minute.");
        System.out.println();
        try {
            TimeUnit.MINUTES.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static ResponseEntity<Map<String,Object>> configureResponse(Page<?> page, String URL) {
        Integer totalPages = page.getTotalPages();
        Integer items = page.getNumberOfElements();
        HashMap<String, Object> map = new HashMap<>();
        HashMap<String,String> links = new HashMap<>();
        links.put("first",URL + "?page=1");
        if(page.hasNext()) {
            links.put("next",URL + "?page=" + (page.nextPageable().getPageNumber()+1));
        }
        if(page.hasPrevious() && (page.getNumber() <= totalPages)) {
            links.put("prev",URL + "?page=" + (page.previousPageable().getPageNumber()+1));
        }
        links.put("last",URL + "?page=" + totalPages);
        if (page.getNumberOfElements() == 0) {
            map.put("error", new RequestException("Данная страница не существует!"));
            map.put("pages", totalPages);
            map.put("items", items);
            map.put("links",links);
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
        else {
            map.put("products", page.getContent());
            map.put("pages", totalPages);
            map.put("items", items);
            map.put("links",links);
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
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
