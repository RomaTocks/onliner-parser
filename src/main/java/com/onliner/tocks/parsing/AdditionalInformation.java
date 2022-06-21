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

    // TODO: 20.11.2021 Доработать метод передачи дополнительной информации.
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
            case "Длина видеокарты" : graphicCard.setLength(value);break;
            case "Высота видеокарты" : graphicCard.setHeight(value);break;
            case "Рекомендуемый блок питания" : graphicCard.setPSU(value);break;
            case "Эффективная частота памяти" : graphicCard.setMemoryFrequency(value);break;
            case "Пропускная способность памяти" : graphicCard.setMemoryBandwidth(value);break;
            case "Ширина шины памяти" : graphicCard.setMemoryBusWidth(value);break;
            case "Поддержка DirectX" : graphicCard.setDirectx(value);break;
            case "Поддержка SLI/CrossFire" : graphicCard.setSliCrossFire(value);break;
            case "Разъёмы питания" : graphicCard.setPowerConnectors(value);break;
            case "Охлаждение" : graphicCard.setCooling(value);break;
            case "Количество вентиляторов" : graphicCard.setFanCount(value);break;
            case "HDMI" : graphicCard.setHdmi(value);break;
            case "DisplayPort" : graphicCard.setDisplayPort(value);break;
            case "mini HDMI" : graphicCard.setMiniHdmi(value);break;
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
            case "Кэш L2" : cpu.setL2cache(value);break;
            case "Кэш L3" : cpu.setL3cache(value);break;
            case "Техпроцесс" : cpu.setTechProcess(value);break;
            case "Многопоточность ядра" : cpu.setMultiThread(value);break;
        }
        return cpu;
    }

    // TODO: 20.11.2021 Доработать информацию о вентиляторах.
    public static FanAdditionalInformation setAdditionalInformationForFan(String key, String value, FanAdditionalInformation fan) {
        switch (key)
        {
            case "Охлаждение" : fan.setCoolingType(value);break;
            case "Цвет" : fan.setColor(value);break;
            case "Сокет" : fan.setSocket(value);break;
            case "Рассеиваемая мощность" : fan.setDispelPower(value);break;
            case "Тепловые трубки" : fan.setHeatPipes(value);break;
            case "Диаметр вентилятора" : fan.setDiameterFan(value);break;
            case "Максимальная скорость вращения" : fan.setMaxSpeed(value);break;
            case "Материал радиатора" : fan.setMaterial(value);break;
            case "Испарительные камеры" : fan.setEvaporation(value);break;
            case "Количество вентиляторов" : fan.setFanCount(value);break;
            case "Подшипник" : fan.setBearing(value);break;
            case "Максимальный воздушный поток" : fan.setFlow(value);break;
            case "Контроль скорости вращения (PWM)" : fan.setPWM(value);break;
            case "Термоконтроль" : fan.setThermalControl(value);break;
            case "Тип подключения" : fan.setConnectType(value);break;
            case "LED-подсветка" : fan.setLED(value);break;
            case "Виброизоляция" : fan.setVibration(value);break;
            case "Максимальный уровень шума" : fan.setNoise(value);break;
            case "Ширина" : fan.setWidth(value);break;
            case "Глубина" : fan.setDepth(value);break;
            case "Высота (толщина)" : fan.setHeight(value);break;
            case "Вес" : fan.setWeight(value);break;
        }
        return fan;
    }

    // TODO: 20.11.2021 Доработать информацию о SSD.
    public static SSDAdditionalInformation setAdditionalInformationForSSD(String key, String value, SSDAdditionalInformation ssd) {
        switch (key)
        {
            case "Дата выхода на рынок" : ssd.setBirthday(value);break;
            case "Адаптер 3.5\"" : ssd.setAdapter(value);break;
            case "Скорость последовательного чтения" : ssd.setReadSpeed(value);break;
            case "Скорость последовательной записи" : ssd.setWriteSpeed(value);break;
            case "Время наработки на отказ (МТBF)" : ssd.setWorkTime(value);break;
            case "Контроллер" : ssd.setController(value);break;
            case "Интерфейс" : ssd.setSsdInterface(value);break;
            case "Тип микросхем Flash" : ssd.setMicroType(value);break;
            case "Форм-фактор" : ssd.setFormFactor(value);break;
            case "Объём" : ssd.setVolume(value);break;
            case "Размеры устройств M.2" : ssd.setM2Size(value);break;
            case "Ресурс записи" : ssd.setRecordResource(value);break;
            case "Аппаратное шифрование" : ssd.setEncryption(value);break;
            case "Охлаждение" : ssd.setCooling(value);break;
            case "Подсветка" : ssd.setBacklight(value);break;
        }
        return ssd;
    }

    // TODO: 20.11.2021 Доработать информацию о HDD.
    public static HDDAdditionalInformation setAdditionalInformationForHDD(String key, String value, HDDAdditionalInformation hdd) {
        switch (key)
        {
            case "Скорость последовательного чтения" : hdd.setReadSpeed(value);break;
            case "Скорость последовательной записи" : hdd.setWriteSpeed(value);break;
            case "Форм-фактор" : hdd.setFormFactor(value);break;
            case "Скорость вращения шпинделя" : hdd.setSpindleSpeed(value);break;
            case "Буфер" : hdd.setBuffer(value);break;
            case "Объём" : hdd.setVolume(value);break;
            case "Интерфейс" : hdd.setHddInterface(value);break;
            case "Аппаратное шифрование" : hdd.setEncryption(value);break;
            case "Размер сектора" : hdd.setSectorSize(value);break;
            case "Уровень шума при чтении/записи" : hdd.setReadOrWriteNoise(value);break;
            case "Уровень шума в режиме ожидания" : hdd.setWaitingNoise(value);break;
            case "Энергопотребление (чтение/запись)" : hdd.setPowerUsage(value);break;
            case "Толщина" : hdd.setDepth(value);break;
        }
        return hdd;
    }

    // TODO: 20.11.2021 Доработать информацию о PSU.
    public static PSUAdditionalInformation setAdditionalInformationForPSU(String key, String value, PSUAdditionalInformation psu) {
        switch (key)
        {
            case "Мощность" : psu.setPower(value);break;
            case "Стандарт блока питания" : psu.setStandard(value);break;
            case "КПД" : psu.setEfficiency(value);break;
            case "Сертификат 80 PLUS" : psu.setCertificate(value);break;
            case "Размер вентилятора блока питания" : psu.setFan(value);break;
            case "CPU 4 pin" : psu.setCpu4pin(value);break;
            case "PCIe 6 pin" : psu.setPcie6pin(value);break;
            case "PCIe 8 pin" : psu.setPcie8pin(value);break;
            case "Высота" : psu.setHeight(value);break;
            case "Ширина" : psu.setWidth(value);break;
            case "Глубина" : psu.setDepth(value);break;
            case "IDE 4 pin" : psu.setIde4pin(value);break;
            case "Диапазон входного напряжения сети" : psu.setRange(value);break;
        }
        return psu;
    }

    // TODO: 20.11.2021 Доработать информацию о материнских платах.
    public static MotherboardAdditionalInformation setAdditionalInformationForMotherboard(String key, String value, MotherboardAdditionalInformation motherboard) {
        switch (key)
        {
            case "Дата выхода на рынок" : motherboard.setBirthday(value);break;
            case "Сокет" : motherboard.setSocket(value);break;
            case "Форм-фактор" : motherboard.setFormFactor(value);break;
            case "Максимальный объём памяти" : motherboard.setMaxMemory(value);break;
            case "Режим памяти" : motherboard.setMemoryMode(value);break;
            case "Максимальная частота памяти" : motherboard.setMaxMemoryFrequency(value);break;
            case "M.2" : motherboard.setM2(value);break;
            case "SATA 3.0" : motherboard.setSata3(value);break;
            case "Поддержка процессоров" : motherboard.setCpuSupport(value);break;
            case "Чипсет" : motherboard.setChipset(value);break;
            case "Подсветка" : motherboard.setBacklight(value);break;
            case "Тип памяти" : motherboard.setMemoryType(value);break;
            case "Количество слотов памяти" : motherboard.setMemorySlots(value);break;
            case "Длина" : motherboard.setLength(value);break;
            case "Ширина" : motherboard.setWidth(value);break;
            case "Wi-Fi" : motherboard.setWifi(value);break;
            case "Поддержка встроенной графики" : motherboard.setIntegratedGraphics(value);break;
            case "Поддержка SLi/CrossFire" : motherboard.setSli(value);break;
            case "HDMI" : motherboard.setHdmi(value);break;
        }
        return motherboard;
    }

    // TODO: 20.11.2021 Доработать информацию о памяти.
    public static RamAdditionalInformation setAdditionalInformationForRam(String key, String value, RamAdditionalInformation ram) {
        switch (key)
        {
            case "Набор" : ram.setKit(value);break;
            case "Общий объем" : ram.setValue(value);break;
            case "Объем одного модуля" : ram.setSingleValue(value);
            case "Тип" : ram.setType(value);break;
            case "Частота" : ram.setFrequency(value);break;
            case "Тайминги" : ram.setTiming(value);break;
            case "Профили XMP" : ram.setXmp(value);break;
        }
        return ram;
    }

    // TODO: 28.11.2021 Доработать информацию о корпусах.
    public static ChassisAdditionalInformation setAdditionalInformationForChassis(String key, String value, ChassisAdditionalInformation chassis) {
        switch (key)
        {
            case "Блок питания" : chassis.setPsuKit(value);break;
            case "Тип корпуса" : chassis.setType(value);break;
            case "Цвет корпуса" : chassis.setColor(value);break;
            case "Материал окна" : chassis.setWindowMaterial(value);break;
            case "Макс. размер материнской платы" : chassis.setMaxSizeOfMotherboard(value);break;
            case "Совместимые материнские платы" : chassis.setMotherboardsCompatibleSizes(value);break;
            case "Расположение блока питания" : chassis.setPsuLocation(value);break;
            case "Поддержка жидкостного охлаждения" : chassis.setWaterCoolingSupport(value);break;
            case "Количество мест для вентиляторов" : chassis.setFanSection(value);break;
            case "Установленные вентиляторы" : chassis.setFanKit(value);break;
            case "Макс. длина видеокарты" : chassis.setMaxGPULength(value);break;
            case "Макс. высота процессорного кулера" : chassis.setMaxCPUCoolingSystemHeight(value);break;
            case "Макс. длина блока питания" : chassis.setMaxPSULength(value);break;
            case "Высота" : chassis.setHeight(value);break;
            case "Ширина" : chassis.setWidth(value);break;
            case "Глубина" : chassis.setDepth(value);break;
            case "Вес" : chassis.setWeight(value);break;
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
