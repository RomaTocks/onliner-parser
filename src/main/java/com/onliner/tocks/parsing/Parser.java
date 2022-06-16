package com.onliner.tocks.parsing;

import com.onliner.tocks.model.product.Product;
import com.onliner.tocks.parsing.common.ProductsEnum;
import com.onliner.tocks.parsing.common.image.Images;
import com.onliner.tocks.parsing.common.price.Price;
import com.onliner.tocks.parsing.common.product.Page;
import com.onliner.tocks.parsing.common.product.Products;
import com.onliner.tocks.parsing.common.sellers.Position;
import com.onliner.tocks.parsing.common.sellers.Sellers;
import com.onliner.tocks.parsing.common.tdp.TDP;
import com.onliner.tocks.parsing.converter.Converter;
import com.onliner.tocks.parsing.filters.ProductFilters;
import com.onliner.tocks.parsing.filters.items.Item;
import com.onliner.tocks.transform.Transform;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
        list = parseAdditionalInformation(list,productsEnum, true);
        list = parseSellers(list);
        return list;
    }
    // TODO: 20.11.2021 Рефакторинг метода.
    public List<? extends Product> updateParsing(List<? extends Product> list, ProductsEnum productsEnum) {
        List<? extends Product> products = baseParsing(productsEnum);
        list.forEach(product ->
                products.stream()
                        .filter(productFromRequest -> product.getId().equals(productFromRequest.getId()))
                        .findFirst()
                        .ifPresent(product::setInformation));
        list.stream().filter(product ->
                products.stream()
                        .noneMatch(productFromRequest -> product.getId().equals(productFromRequest.getId())))
                        .forEach(product -> product.setPrices(null));
        products.removeIf(product ->
                list.stream()
                    .anyMatch(productFromRequest -> product.getId().equals(productFromRequest.getId())));
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
            Long endingTime = System.currentTimeMillis();
            long time = (endingTime - startingTime)/1000;
            System.out.println("--------------ПАРСИНГ ОКОНЧЕН ЗА " + time + " СЕКУНД--------------");
            log.info("Total goods: " + allProducts.getTotal());
        }
        return transformList(allProducts != null ? allProducts.getProducts() : new ArrayList<>(),productsEnum);
    }
    public List<Product> parseManufacturer(List<Product> products) {
        RestTemplate template = new RestTemplate();
        log.info("Parsing manufacturer for products");
        AtomicReference<Integer> count = new AtomicReference<>(0);
        AtomicReference<Double> size = new AtomicReference<>((double)products.size());
        products.forEach(product -> {
            count.getAndSet(count.get() + 1);
            if(product.getManufacturer() == null) {
                try {
                    log.info("Parsing manufacturer for " + product.getName());
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
                catch (Exception exception) {
                    log.error("Exception while parsing manufacturer for product " + product.getName());
                }
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
                    log.info("Starting update of product's rating.");
                    try
                    {
                        Product rating = template.getForObject("https://catalog.api.onliner.by/products/" + product.getKey(), Product.class);
                        if (rating != null)
                        {
                            product.setReviews(rating.getReviews());
                            log.info("Rating of product are updated.");
                        }
                        else {
                            log.info("Reviews for product not found.");
                        }
                    }
                    catch (Exception exception) {
                       log.error("Exception while parsing rating for product " + product.getName());
                    }
                    log.info("Completed : " + String.format("%.2f", (current.get() / size) * 100) + "%");
                    getTimeout();
                }
                catch (Exception e)
                {
                    log.error("Exception while parsing sellers for product " + product.getName());
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
    public List<TDP> parseTDP(ProductFilters graphicCardFilter, List<TDP> tdpList) {
        WebDriver driver = getWebDriver();
        log.info("Parsing of videocards TDP started.");
        List<Item> graphicCardNames = graphicCardFilter.getDictionaries().get("desktop_gpu");
        System.out.println(graphicCardNames.size());
        graphicCardNames.removeIf(product ->
                tdpList.stream()
                        .anyMatch(productFromRequest -> product.getId().equals(productFromRequest.getId())));
        System.out.println(graphicCardNames.size());
        List<TDP> tdps = new ArrayList<>();
        Double size = (double) graphicCardNames.size();
        AtomicReference<Double> current = new AtomicReference<>((double) 0);
        try {
            graphicCardNames.forEach(item -> {
                current.getAndSet(current.get() + 1);
                TDP tdp = new TDP();
                driver.get("https://www.techpowerup.com/gpu-specs/");
                WebElement input = driver.findElement(new By.ById("quicksearch"));
                getFixedTimeout(4, TimeUnit.SECONDS);
                input.sendKeys(item.getName());
                input.submit();
                log.info("Search TDP for " + item.getName());
                tdp.setId(item.getId());
                tdp.setName(item.getName());
                getFixedTimeout(4,TimeUnit.SECONDS);
                Document document = Jsoup.parse(driver.getPageSource());
                Element ajax = document.getElementById("ajaxresults");
                Element table = ajax.getElementsByTag("table").first();
                if( table != null) {
                    Element tbody = table.getElementsByTag("tbody").first();
                    Element tr = tbody.getElementsByTag("tr").first();
                    Element td = tr.getElementsByTag("td").first();
                    String href = td.getElementsByTag("a").first().attr("href");
                    log.info("Started parsing TDP of " + item.getName() + " from " + "https://www.techpowerup.com" + href);
                    driver.get("https://www.techpowerup.com" + href);
                    getFixedTimeout(4,TimeUnit.SECONDS);
                    document = Jsoup.parse(driver.getPageSource());
                    String TDP = document.select("dt:contains(TDP)").first().nextElementSibling().text();
                    tdp.setTdp(TDP);
                    tdp.setTdpValue(Converter.getIntegersFromString(TDP));
                    tdps.add(tdp);
                    log.info("TDP:" + tdp);
                    log.info("Completed : " + String.format("%.2f", (current.get() / size) * 100) + "%, " + current + " from " + graphicCardNames.size());
                }
            });
        }
        catch (Exception exception) {
            log.error("Exception while parsing TDP.");
        }
        driver.close();
        log.info("Parsing of TDPs ended.");
        return tdps;
    }

    public List<? extends Product> parseAdditionalInformation(List<? extends Product> productsList, ProductsEnum productsEnum, boolean update) {
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
                if(isAdditionalInformationByEnum(product,productsEnum) && newProduct.getPrices() != null || update) {
                    log.info("Started parsing additional information about " + product.getName() +  " from " + product.getHtml_url());
                    driver.get(product.getHtml_url());
                    Document document = Jsoup.parse(driver.getPageSource());
                    log.info("Started search of product's images.");
                    Images images = parseImages(product, document).getImages();
                    newProduct.setImages(images);
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
                    log.info("Images: " + newProduct.getImages());
                    log.info("Additional Information for " + product.getName() + " parsed.");
                    log.info("keys: " + keys);
                    log.info("values: " + values);
                }
                else {
                    Document document = Jsoup.parse(driver.getPageSource());
                    newProduct = getAdditionalInformationOfProduct(newProduct, product);
                    log.info("Additional information for product " + newProduct.getName() + " already exist.");
                    log.info("Started search of product's images.");
                    Images images = parseImages(product, document).getImages();
                    newProduct.setImages(images);
                }
                list.add(newProduct);
                log.info("Completed : " + String.format("%.2f", (current.get() / size) * 100) + "%");
                System.out.println();
            }
            catch (Exception exception) {
                log.error("Exception while parsing additional information.");
            }
        });
        List<Product> productsWithManufacturer = parseManufacturer(list);
        List<? extends Product> productsWithValues = Transform.getValuesFromAdditional(productsWithManufacturer,productsEnum);
        driver.close();
        log.info("Ended parsing additional information for products.");
        return productsWithValues;
    }
    public Product parseImages(Product product, Document document) {
        log.info("Parsing images for " + product.getName());
        List<String> imagesURI = new ArrayList<>();
        Elements galleryWrapper;
        Images productImages = new Images();
        if(product.getImages() == null || product.getImages().getImages() == null) {
            if (product.getImages() != null && product.getImages().getHeader() != null)
            {
                    productImages = product.getImages();
            }
            else {
                if(product.getImages().getHeader() != null) {
                    productImages.setHeader(product.getImages().getHeader());
                }
            }
            Element images = document.getElementById("product-gallery");
            if (images != null)
            {
                galleryWrapper = images.getElementsByClass("product-gallery__shaft");
                Element gallery = galleryWrapper.first();
                if (gallery != null)
                {
                    Elements imagesWrappers = gallery.getAllElements();
                    for (Element image : imagesWrappers)
                    {
                        String imageURL = image.attr("data-original");
                        if(!imageURL.trim().isEmpty()) {
                            imagesURI.add(imageURL);
                        }
                    }
                }
                productImages.setImages(imagesURI);
                product.setImages(productImages);
            }
            else {
                log.info("Not found images for product " + product.getName());
            }
        }
        else {
            log.info("Images for product " + product.getName() + " already exist!");
        }
        return product;
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
