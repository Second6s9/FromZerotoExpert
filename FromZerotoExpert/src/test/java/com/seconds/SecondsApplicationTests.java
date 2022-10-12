package com.seconds;

import com.seconds.dao.WebInformationDao;
import com.seconds.entity.WebInformation;
import com.seconds.schedule.task.WebInformationTask;
import com.seconds.service.WebInformationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;

@SpringBootTest
class SecondsApplicationTests {
    @Autowired
    private WebInformationDao webInformationDao;

    @Autowired
    private WebInformationService webInformationService;

    @Autowired
    private WebInformationTask webInformationTask;

    @Test
    void contextLoads() {
    }

    @Test
    void webInformationDaoTest(){
//        WebInformation w1 = new WebInformation();
//
//        WebInformation w2 = new WebInformation();
//        Date date = new Date(System.currentTimeMillis());
//        webInformationDao.deleteById(3);
//        System.out.println("seconds:" + webInformationDao.getWebInformationByDate(date));
//        System.out.println("totalNums:" + webInformationDao.getTotalNums());
//        System.out.println("minId:" + webInformationDao.getMinId());

        System.out.println("ip = " + webInformationDao.getRangeIpNum(24,27));
        System.out.println("pv = " + webInformationDao.getRangePvNum(24,27));
        System.out.println("uv = " + webInformationDao.getRangeUvNum(24,27));
    }

    @Test
    void webInformationServiceTest(){
//        webInformationTask.executeInternal();
    }
}
