package com.onliner.tocks.parsing.common;

import com.onliner.tocks.model.*;
import com.onliner.tocks.parsing.common.product.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductEnumsMethods
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
            case CHASSIS: product = new Chassis();break;
        }
        return product;
    }
    public static String getProductsUrlByEnum(ProductsEnum productsEnum) {
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
            case CHASSIS: URL = "https://catalog.onliner.by/sdapi/catalog.api/search/chassis";break;
            default:
                throw new IllegalStateException("Unexpected value: " + productsEnum);
        }
        return URL;
    }
    public static String getFiltersUrlByEnum(ProductsEnum productsEnum) {
        String URL;
        switch (productsEnum) {
            case PROCESSORS : URL = "https://catalog.onliner.by/sdapi/catalog.api/facets/cpu";break;
            case GRAPHIC_CARDS: URL = "https://catalog.onliner.by/sdapi/catalog.api/facets/videocard";break;
            case RAM: URL = "https://catalog.onliner.by/sdapi/catalog.api/facets/dram";break;
            case SSD: URL = "https://catalog.onliner.by/sdapi/catalog.api/facets/ssd";break;
            case HDD: URL = "https://catalog.onliner.by/sdapi/catalog.api/facets/hdd";break;
            case COOLING: URL = "https://catalog.onliner.by/sdapi/catalog.api/facets/fan";break;
            case PSU: URL = "https://catalog.onliner.by/sdapi/catalog.api/facets/powersupply";break;
            case MOTHERBOARDS: URL = "https://catalog.onliner.by/sdapi/catalog.api/facets/motherboard";break;
            case CHASSIS: URL = "https://catalog.onliner.by/sdapi/catalog.api/facets/chassis";break;
            default:
                throw new IllegalStateException("Unexpected value: " + productsEnum);
        }
        return URL;
    }
}
