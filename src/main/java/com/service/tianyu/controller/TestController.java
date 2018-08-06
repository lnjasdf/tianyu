package com.service.tianyu.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lnjasdf on 2017/1/13.
 */
@Controller
@RequestMapping("/")
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @RequestMapping("test")
    @ResponseBody
    public String test() {
        logger.info("aaaaas");
        return "hello world!";
    }
}
