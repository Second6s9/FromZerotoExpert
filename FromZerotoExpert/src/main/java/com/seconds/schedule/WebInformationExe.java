package com.seconds.schedule;

import com.seconds.schedule.task.WebInformationTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Configuration  //标记配置类
@EnableScheduling   //开启定时任务
@Component
public class WebInformationExe {
    @Autowired
    private WebInformationTask webInformationTask;


    @Scheduled(cron = "0 18 10 * * ?")
    public void autoIntoDbTask(){
        webInformationTask.executeInternal();
    }
}
