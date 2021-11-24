package com.onliner.tocks.parsing;

import com.onliner.tocks.parsing.common.General;
import com.onliner.tocks.parsing.common.Page;
import com.onliner.tocks.parsing.common.Product;
import com.onliner.tocks.parsing.common.Products;
import com.onliner.tocks.parsing.common.sellers.Position;
import com.onliner.tocks.parsing.common.sellers.Sellers;
import lombok.extern.slf4j.Slf4j;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static com.onliner.tocks.parsing.AdditionalInformation.getAdditionalInformationOfProduct;
import static com.onliner.tocks.parsing.AdditionalInformation.setAdditionalInformationForProductFromMap;
import static com.onliner.tocks.parsing.common.General.*;


@Component
@Slf4j
public class Parser {

    // TODO: 20.11.2021 Рефакторинг метода.
    public List<? extends Product> fullInformationParsing(ProductsEnum productsEnum) {
        List<? extends Product> list = baseParsing(productsEnum);
        getTimeout();
        list = parseAdditionalInformation(list,productsEnum);
        list = parseSellers(list);
        return list;
    }
    // TODO: 20.11.2021 Рефакторинг метода.
    public List<? extends Product> updateParsing(List<? extends Product> list, ProductsEnum productsEnum) {
        List<? extends Product> products = baseParsing(productsEnum);
        list.forEach(product -> products.stream().filter(productFromRequest -> product.getId().equals(productFromRequest.getId())).findFirst().ifPresent(product::setInformation));
        list.stream().filter(product -> products.stream().noneMatch(productFromRequest -> product.getId().equals(productFromRequest.getId()))).forEach(product -> product.setPrices(null));
        products.removeIf(product -> list.stream().anyMatch(productFromRequest -> product.getId().equals(productFromRequest.getId())));
        ((List<Product>) list).addAll(products);
        return parseSellers(list);
    }
    // TODO: 20.11.2021 Рефакторинг метода.
    public List<? extends Product> baseParsing(ProductsEnum productsEnum) {
        Long startingTime = System.currentTimeMillis();
        RestTemplate template = new RestTemplate();
        HashMap<String,String> params = new HashMap<>();
        log.info("Parsing goods started!");
        log.info("Parsing from " + getUrlByEnum(productsEnum));
        Products allProducts = new Products();
        try
        {
            allProducts = template.getForObject(getUrlByEnum(productsEnum) + (productsEnum == ProductsEnum.COOLING ? "&" : "?") + "limit=36&page=1&price[from]=0.00", Products.class,params);
        }
        catch (RestClientException e)
        {
            log.error("Impossible to parse from " + getUrlByEnum(productsEnum));
        }
        if (allProducts != null)
        {
            log.info("Current page is : " + allProducts.getPage().getCurrent());
            Page page = allProducts.getPage();
            log.info("Parsing goods ended.");
            log.info("Total pages: " + page.getLast());
            Double size = Double.valueOf(page.getLast());
            Double currentProductNumber = 0.0;
            while (page.getCurrent() < page.getLast()) {
                currentProductNumber++;
                page.incrementCurrent();
                log.info("Current page is : " + page.getCurrent() + " of " + page.getLast());
                try {
                    log.info("Parse...");
                    Products products = template.getForObject(getUrlByEnum(productsEnum) + (productsEnum == ProductsEnum.COOLING ? "&" : "?") + "price[from]=0.00&limit=36&page=" + page.getCurrent(), Products.class,params);
                    if (products != null)
                    {
                        allProducts.addAllNewProducts(products.getProducts());
                    }
                    else {
                        throw new NullPointerException();
                    }
                    log.info(currentProductNumber + " from " + size);
                    log.info("Completed : " + (currentProductNumber / size) * 100 + "%");
                }
                catch (NullPointerException e)
                {
                    log.error("Impossible to parse from " + getUrlByEnum(productsEnum));
                }
                getTimeout();
            }
            Long endingTime = System.currentTimeMillis();
            long time = (endingTime - startingTime)/1000;
            System.out.println("--------------ПАРСИНГ ОКОНЧЕН ЗА " + time + " СЕКУНД--------------");
            log.info("Total goods: " + allProducts.getTotal());
        }
        return transformList(allProducts != null ? allProducts.getProducts() : new ArrayList<>(),productsEnum);
    }

    // TODO: 20.11.2021 Рефакторинг метода.
    public List<? extends Product> parseSellers(List<? extends Product> products) {
        Double size = (double) products.size();
        AtomicReference<Double> current = new AtomicReference<>((double) 0);
        products.forEach(product -> {
            current.getAndSet(current.get() + 1);
            if(product.getPrices() != null) {
                try {
                    RestTemplate template = new RestTemplate();
                    HashMap<String,String> params = new HashMap<>();
                    log.info("Parsing prices for " + product.getName() + " from https://catalog.onliner.by/sdapi/shop.api/products/" + product.getKey() + "/positions?town=all&has_prime_delivery=1&town_id=17030");
                    Sellers sellers = template.getForObject("https://catalog.onliner.by/sdapi/shop.api/products/" + product.getKey() + "/positions?town=all&has_prime_delivery=1&town_id=17030", Sellers.class,params);
                    log.info("Parsing ended.");
                    List<Position> positions = sellers.getPositions().getPrimary();
                    List<Position> newPositions = new ArrayList<>();
                    positions.forEach(position -> sellers.getShops().values().forEach(shop -> {
                        if (shop.getId().equals(position.getShop_id())) {
                            position.setShopInformation(shop);
                            newPositions.add(position);
                        }
                    }));
                    product.setPositions(newPositions);
                    log.info("Completed : " + String.format("%.2f", (current.get() / size) * 100) + "%");
                    getTimeout();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            else {
                log.info("Product " + product.getName() + " is no longer for sale.");
                log.info("Parsing ended.");
                log.info("Completed : " + String.format("%.2f", (current.get() / size) * 100) + "%");
                product.setPositions(null);
            }
        });
        return products;
    }
    public List<? extends Product> parseAdditionalInformation(List<? extends Product> productsList, ProductsEnum productsEnum) {
        FirefoxOptions options = new FirefoxOptions();
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("general.useragent.override","Opera/9.80 (Windows NT 5.1; U; ru) Presto/2.9.168 Version/11.50");
        profile.setPreference("network.proxy.type", 1);
        options.setHeadless(true);
        options.setProfile(profile);
        WebDriver driver = new FirefoxDriver(options);
        driver.manage().timeouts().pageLoadTimeout(100000, TimeUnit.MILLISECONDS);
        List<Product> list = new ArrayList<>();
        log.info("Started parsing additional information for products.");
        Double size = (double) productsList.size();
        AtomicReference<Double> current = new AtomicReference<>((double) 0);
        productsList.forEach(product -> {
            current.getAndSet(current.get() + 1);
            try {
                Product newProduct = General.getObjectByEnum(productsEnum);
                newProduct.setInformation(product);
                if(isAdditionalInformationByEnum(product,productsEnum) && newProduct.getPrices() != null) {
                    log.info("Started parsing additional information about " + product.getName() +  " from " + product.getHtml_url());
                    driver.get(product.getHtml_url());
                    Document document = Jsoup.parse(driver.getPageSource());
                    Elements elements = document.getElementsByTag("tbody");
                    List<String> values = new ArrayList<>();
                    List<String> keys = new ArrayList<>();
                    for (Element element : elements) {
                        for (Element tr : element.getElementsByTag("tr")) {
                            try {
                                keys.add(tr.selectFirst("td").ownText());
                                if(tr.select("td").last().select("span").last().hasClass("i-tip")) {
                                    values.add("Да");
                                }
                                if(tr.select("td").last().select("span").last().hasClass("i-x")) {
                                    values.add("Нет");
                                }
                                if(tr.select("td").last().select("span").last().hasClass("value__text")) {
                                    values.add(tr.select("td").last().select("span").text());
                                }
                            }
                            catch (NullPointerException exception)
                            {
                            }
                        }
                    }
                    LinkedHashMap<String, String> additionalInformation = listsToHashMap(keys,values);
                    newProduct = setAdditionalInformationForProductFromMap(additionalInformation, newProduct);
                    log.info("Additional Information for " + product.getName() + " parsed.");
                    log.info("keys: " + keys);
                    log.info("values: " + values);
                }
                else {
                    newProduct = getAdditionalInformationOfProduct(newProduct, product);
                    log.info("Additional information for product " + newProduct.getName() + " already exist.");
                }
                list.add(newProduct);
                log.info("Completed : " + String.format("%.2f", (current.get() / size) * 100) + "%");
                System.out.println();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        driver.close();
        log.info("Ended parsing additional information for products.");
        return list;
    }
}
