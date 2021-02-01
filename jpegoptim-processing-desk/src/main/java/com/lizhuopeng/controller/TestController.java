package com.lizhuopeng.controller;


import com.lizhuopeng.model.TestModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/test")
    public TestModel returnObject(){
        return new TestModel("li",18);
    }
}
