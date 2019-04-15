package com.jxbig.sharp.eurekaclient.common.config;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class PersonListener implements ApplicationListener<PersonEvent> {
    @Override
    public void onApplicationEvent(PersonEvent personEvent) {
        System.out.println(personEvent);
    }
}
