package com.onliner.tocks.parsing;

import com.onliner.tocks.parsing.common.ProductsEnum;
import com.onliner.tocks.parsing.common.price.Price;
import com.onliner.tocks.parsing.common.product.Page;
import com.onliner.tocks.parsing.common.product.Product;
import com.onliner.tocks.parsing.common.product.Products;
import com.onliner.tocks.parsing.common.sellers.Position;
import com.onliner.tocks.parsing.common.sellers.Sellers;
import com.onliner.tocks.parsing.filters.ProductFilters;
import com.onliner.tocks.transform.Transform;
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

import static com.onliner.tocks.parsing.AdditionalInformation.*;
import static com.onliner.tocks.parsing.common.ProductEnumsMethods.*;
import static com.onliner.tocks.timeout.Timeout.getFixedTimeout;
import static com.onliner.tocks.timeout.Timeout.getTimeout;
import static com.onliner.tocks.transform.Transform.listsToHashMap;
import static com.onliner.tocks.transform.Transform.transformList;


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
        log.info("Parsing from " + getProductsUrlByEnum(productsEnum));
        Products allProducts = new Products();
        List<Product> productsWithManufacturer = new ArrayList<>();
        try
        {
            allProducts = template.getForObject(getProductsUrlByEnum(productsEnum) + (productsEnum == ProductsEnum.COOLING ? "&" : "?") + "limit=36&page=1&price[from]=0.00", Products.class,params);
        }
        catch (RestClientException e)
        {
            log.error("Impossible to parse from " + getProductsUrlByEnum(productsEnum));
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
                    Products products = template.getForObject(getProductsUrlByEnum(productsEnum) + (productsEnum == ProductsEnum.COOLING ? "&" : "?") + "price[from]=0.00&limit=36&page=" + page.getCurrent(), Products.class,params);
                    if (products != null)
                    {
                        allProducts.addAllNewProducts(products.getProducts());
                    }
                    else {
                        throw new NullPointerException();
                    }
                    log.info("Completed : " + String.format("%.2f",(currentProductNumber / size) * 100) + "%");
                }
                catch (NullPointerException e)
                {
                    log.error("Impossible to parse from " + getProductsUrlByEnum(productsEnum));
                }
                getFixedTimeout(500, TimeUnit.MILLISECONDS);
            }
            productsWithManufacturer = parseManufacturer(allProducts.getProducts());
            Long endingTime = System.currentTimeMillis();
            long time = (endingTime - startingTime)/1000;
            System.out.println("--------------ПАРСИНГ ОКОНЧЕН ЗА " + time + " СЕКУНД--------------");
            log.info("Total goods: " + allProducts.getTotal());
        }
        return transformList(allProducts != null ? productsWithManufacturer : new ArrayList<>(),productsEnum);
    }
    public List<Product> parseManufacturer(List<Product> products) {
        RestTemplate template = new RestTemplate();
        log.info("Parsing manufacturer for products");
        AtomicReference<Integer> count = new AtomicReference<>(0);
        AtomicReference<Double> size = new AtomicReference<>((double)products.size());
        products.forEach(product -> {
            count.getAndSet(count.get() + 1);
            if(product.getManufacturer() == null) {
                Product productWithManufacturer = template.getForObject("https://catalog.api.onliner.by/products/" + product.getKey(), Product.class);
                if (productWithManufacturer != null) {
                    product.setManufacturer(productWithManufacturer.getManufacturer());
                    log.info("Manufacturer key:" + product.getManufacturer().getKey() + " name:" + product.getManufacturer().getName());
                }
                else {
                    log.info("Manufacturer for product " + product.getName() + " doesn't exist!");
                }
                getTimeout();
            }
            else {
                log.info("Manufacturer for " + product.getName() + " already parsed!");
            }
            log.info("Completed : " + String.format("%.2f", (count.get() / size.get()) * 100) + "%");
        });
        return products;
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
                    if (sellers != null && (sellers.getPositions().getPrimary() != null || !sellers.getPositions().getPrimary().isEmpty()))
                    {
                        List<Position> positions = sellers.getPositions().getPrimary();
                        List<Position> newPositions = new ArrayList<>();
                        positions.forEach(position -> sellers.getShops().values().forEach(shop -> {
                            if (shop.getId().equals(position.getShop_id()))
                            {
                                position.setShopInformation(shop);
                                Price priceWithValue = position.getPosition_price();
                                priceWithValue.setValue(Double.parseDouble(priceWithValue.getAmount()));
                                position.setPosition_price(priceWithValue);
                                newPositions.add(position);
                            }
                        }));
                        product.setPositions(newPositions);
                    }
                    else {
                        log.info("Positions for product " + product.getName() + " doesn't exist!");
                    }
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
        WebDriver driver = getWebDriver();
        List<Product> list = new ArrayList<>();
        log.info("Started parsing additional information for products.");
        Double size = (double) productsList.size();
        AtomicReference<Double> current = new AtomicReference<>((double) 0);
        productsList.forEach(product -> {
            current.getAndSet(current.get() + 1);
            try {
                Product newProduct = getObjectByEnum(productsEnum);
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
                            catch (NullPointerException exception) {}
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
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        List<? extends Product> productsWithValues = Transform.getValuesFromAdditional(list,productsEnum);
        driver.close();
        log.info("Ended parsing additional information for products.");
        return productsWithValues;
    }

    public List<ProductFilters> parseProductFilters() {
        RestTemplate template = new RestTemplate();
        List<ProductFilters> productFilters = new ArrayList<>();
        for (ProductsEnum value : ProductsEnum.values())
        {
            log.info("Parsing filters from : " + getFiltersUrlByEnum(value));
            try {
                ProductFilters filters = template.getForObject(getFiltersUrlByEnum(value),ProductFilters.class);
                productFilters.add(filters);
                if (filters != null)
                {
                    filters.setId(value.name());
                    log.info("Filter for " + value.name() + " parsed.");
                }
                else
                {
                    throw new RestClientException("Exception while parsing filters.");
                }
            }
            catch (RestClientException e)
            {
                log.error(e.getMessage());
            }
        }
        return productFilters;
    }

    private WebDriver getWebDriver() {
        FirefoxOptions options = new FirefoxOptions();
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("general.useragent.override","Opera/9.80 (Windows NT 5.1; U; ru) Presto/2.9.168 Version/11.50");
        profile.setPreference("network.proxy.type", 1);
        options.setHeadless(true);
        options.setProfile(profile);
        WebDriver driver = new FirefoxDriver(options);
        driver.manage().timeouts().pageLoadTimeout(100000, TimeUnit.MILLISECONDS);
        return driver;
    }
}
