package com.lizhuopeng.controller;


import com.lizhuopeng.model.TestModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class TestController {
    @GetMapping("/test")
    public TestModel returnObject(){
        int timeNumber = 3;
        //int i=10/0;
        try { TimeUnit.SECONDS.sleep(timeNumber); }catch (Exception e) {e.printStackTrace();}
        return new TestModel("li",18);
    }
}
