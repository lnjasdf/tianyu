package com.service.tianyu;

import com.service.tianyu.dao.ProductConfigDao;
import com.service.tianyu.po.ProductConfigPo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class TianyuApplicationTests {

    @Autowired
    private ProductConfigDao productConfigDao;

    public static void main(String[] args) {
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(dateTime.getDayOfWeek().getValue());
        System.out.println(dateTime.getHour());
        System.out.println(dateTime.getMinute());
    }
}
