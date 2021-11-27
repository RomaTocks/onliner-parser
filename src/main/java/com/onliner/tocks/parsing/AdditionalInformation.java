package com.onliner.tocks.parsing;

import com.onliner.tocks.model.*;
import com.onliner.tocks.model.additional.*;
import com.onliner.tocks.parsing.common.Product;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
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
        return product;
    }

    // TODO: 20.11.2021 Доработать метод передачи дополнительной информации.
    public static Product getAdditionalInformationOfProduct(Product productIn, Product productOut) {
        if(productIn instanceof CPU) {
            ((CPU) productIn).setAdditional(((CPU)productOut).getAdditional());
        }
        if (productIn instanceof GraphicCard) {
            ((GraphicCard) productIn).setAdditional(((GraphicCard)productOut).getAdditional());
        }
        return productIn;
    }

    // TODO: 20.11.2021 Доработать информацию о GPU.
    public static GraphicCardAdditionalInformation setAdditionalInformationForGraphicCard(String key, String value, GraphicCardAdditionalInformation graphicCard) {
        switch (key)
        {
            case "Дата выхода на рынок" : graphicCard.setBirthday(value);break;
            case "Интерфейс" : graphicCard.setGpuInterface(value);break;
            case "Производитель графического процессора" : graphicCard.setManufacturer(value);break;
            case "Графический процессор" : graphicCard.setGpu(value);break;
            case "«Разогнанная» версия" : graphicCard.setBoost(value);break;
            case "Защита от майнинга (LHR)" : graphicCard.setLhr(value);break;
            case "Базовая (референсная) частота графического процессора" : graphicCard.setBaseFrequency(value);break;
            case "Максимальная частота графического процессора" : graphicCard.setTurboFrequency(value);break;
            case "Количество потоковых процессоров" : graphicCard.setStreamCores(value);break;
        }
        return graphicCard;
    }

    // TODO: 20.11.2021 Доработать информацию о CPU.
    public static CPUAdditionalInformation setAdditionalInformationForProcessors(String key, String value, CPUAdditionalInformation cpu) {
        switch (key)
        {
            case "Модельный ряд" : cpu.setCpuModel(value);break;
            case "Тип поставки" : cpu.setCpuPackage(value);break;
            case "Сокет" : cpu.setSocket(value);break;
            case "Дата выхода на рынок" : cpu.setBirthday(value);break;
            case "Охлаждение в комплекте" : cpu.setStockCooling(value);break;
            case "Количество ядер" : cpu.setCores(value);break;
            case "Максимальное количество потоков" : cpu.setStreams(value);break;
            case "Базовая тактовая частота" : cpu.setBaseFrequency(value);break;
            case "Максимальная частота" : cpu.setTurboFrequency(value);break;
            case "Расчетная тепловая мощность (TDP)" : cpu.setTdp(value);break;
            case "Кодовое название кристалла" : cpu.setCodename(value);break;
            case "Поддержка памяти" : cpu.setCpuRam(value);break;
            case "Количество каналов памяти" : cpu.setCpuRamChannel(value);break;
            case "Макс. частота памяти" : cpu.setRamFrequency(value);break;
            case "Встроенный контроллер PCI Express" : cpu.setCpuPciExpress(value);break;
            case "Встроенная графика" : cpu.setCpuGraphics(value);break;
        }
        return cpu;
    }

    // TODO: 20.11.2021 Доработать информацию о вентиляторах.
    public static FanAdditionalInformation setAdditionalInformationForFan(String key, String value, FanAdditionalInformation fan) {
        switch (key)
        {
            case "Охлаждение" : fan.setCoolingType(value);
            case "Цвет" : fan.setColor(value);
            case "Сокет" : fan.setSocket(value);
            case "Рассеиваемая мощность" : fan.setDispelPower(value);
            case "Тепловые трубки" : fan.setHeatPipes(value);
            case "Диаметр вентилятора" : fan.setDiameterFan(value);
            case "Максимальная скорость вращения" : fan.setMaxSpeed(value);
        }
        return fan;
    }

    // TODO: 20.11.2021 Доработать информацию о SSD.
    public static SSDAdditionalInformation setAdditionalInformationForSSD(String key, String value, SSDAdditionalInformation ssd) {
        switch (key)
        {
            case "Дата выхода на рынок" : ssd.setBirthday(value);
            case "Адаптер 3.5\"" : ssd.setAdapter(value);
            case "Скорость последовательного чтения" : ssd.setReadSpeed(value);
            case "Скорость последовательной записи" : ssd.setWriteSpeed(value);
            case "Время наработки на отказ (МТBF)" : ssd.setWorkTime(value);
            case "Контроллер" : ssd.setController(value);
            case "Интерфейс" : ssd.setSsdInterface(value);
            case "Тип микросхем Flash" : ssd.setMicroType(value);
            case "Форм-фактор" : ssd.setFormFactor(value);
            case "Объём" : ssd.setVolume(value);
        }
        return ssd;
    }

    // TODO: 20.11.2021 Доработать информацию о HDD.
    public static HDDAdditionalInformation setAdditionalInformationForHDD(String key, String value, HDDAdditionalInformation hdd) {
        switch (key)
        {
            case "Скорость последовательного чтения" : hdd.setReadSpeed(value);
            case "Скорость последовательной записи" : hdd.setWriteSpeed(value);
            case "Форм-фактор" : hdd.setFormFactor(value);
            case "Скорость вращения шпинделя" : hdd.setSpindleSpeed(value);
            case "Буфер" : hdd.setBuffer(value);
            case "Объём" : hdd.setVolume(value);
        }
        return hdd;
    }

    // TODO: 20.11.2021 Доработать информацию о PSU.
    public static PSUAdditionalInformation setAdditionalInformationForPSU(String key, String value, PSUAdditionalInformation psu) {
        switch (key)
        {
            case "Мощность" : psu.setPower(value);
            case "Стандарт блока питания" : psu.setStandard(value);
            case "КПД" : psu.setEfficiency(value);
            case "Сертификат 80 PLUS" : psu.setCertificate(value);
            case "Размер вентилятора блока питания" : psu.setFan(value);
            case "CPU 4 pin" : psu.setCpu4pin(value);
            case "PCIe 6 pin" : psu.setPcie6pin(value);
            case "PCIe 8 pin" : psu.setPcie8pin(value);
            case "Высота" : psu.setHeight(value);
            case "Ширина" : psu.setWidth(value);
            case "Глубина" : psu.setDepth(value);
            case "IDE 4 pin" : psu.setIde4pin(value);
        }
        return psu;
    }

    // TODO: 20.11.2021 Доработать информацию о материнских платах.
    public static MotherboardAdditionalInformation setAdditionalInformationForMotherboard(String key, String value, MotherboardAdditionalInformation motherboard) {
        switch (key)
        {
            case "Дата выхода на рынок" : motherboard.setBirthday(value);
            case "Сокет" : motherboard.setSocket(value);
            case "Форм-фактор" : motherboard.setFormFactor(value);
            case "Максимальный объём памяти" : motherboard.setMaxMemory(value);
            case "Режим памяти" : motherboard.setMemoryMode(value);
            case "Максимальная частота памяти" : motherboard.setMaxMemoryFrequency(value);
            case "M.2" : motherboard.setM2(value);
            case "SATA 3.0" : motherboard.setSata3(value);
        }
        return motherboard;
    }

    // TODO: 20.11.2021 Доработать информацию о памяти.
    public static RamAdditionalInformation setAdditionalInformationForRam(String key, String value, RamAdditionalInformation ram) {
        switch (key)
        {
            case "Набор" : ram.setKit(value);
            case "Объем" : ram.setValue(value);
            case "Тип" : ram.setType(value);
            case "Частота" : ram.setFrequency(value);
            case "Тайминги" : ram.setTiming(value);
            case "Профили XMP" : ram.setXmp(value);
        }
        return ram;
    }
}