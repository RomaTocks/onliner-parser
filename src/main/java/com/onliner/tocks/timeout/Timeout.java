package com.onliner.tocks.timeout;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class Timeout
{
    public static void getFixedTimeout(Integer time, TimeUnit unit) {
        System.out.println();
        log.info("Fixed timeout:" + time + " " + unit.toString());
        System.out.println();
        getSleep(time, unit);
    }
    public static void getTimeout() {
        int random = (int) (Math.random()*2) + 1;
        System.out.println();
        log.info("Try to avoid ban from onliner.by : Waiting " + random + " seconds.");
        System.out.println();
        getSleep(random, TimeUnit.SECONDS);
    }
    public static void getChapterTimeout() {
        System.out.println();
        log.info("Try to avoid ban from onliner.by : Next chapter, waiting " + 1 + " minute.");
        System.out.println();
        getSleep(1, TimeUnit.MINUTES);
    }
    private static void getSleep(Integer time, TimeUnit unit) {
        try {
            unit.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
