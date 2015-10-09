package com.exodia.back.task;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class AnotherBean {

    public void printAnotherMessage() {
        System.out.println("I am called by Quartz jobBean using CronTriggerFactoryBean");
    }

}