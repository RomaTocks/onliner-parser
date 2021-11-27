package com.onliner.tocks.timeout;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class Timeout
{
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
}
