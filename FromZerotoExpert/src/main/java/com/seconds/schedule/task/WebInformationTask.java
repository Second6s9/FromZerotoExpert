package com.seconds.schedule.task;

import com.seconds.entity.WebInformation;
import com.seconds.service.WebInformationService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class WebInformationTask {
    @Autowired
    private WebInformationService webInformationService;


    public void executeInternal() {
        Date date = new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 2);
        Date tomorrow = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 2);
        WebInformation webInformation = new WebInformation();
        webInformation.setDate(tomorrow);
        webInformation.setPv(0);
        webInformation.setIp(0);
        webInformation.setUv(0);
        webInformationService.scheduledSave(date);
        webInformationService.save(webInformation);
    }


}
