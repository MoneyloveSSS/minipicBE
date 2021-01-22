package com.lizhuopeng.controller;

import com.lizhuopeng.model.TestModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/getOne")
    public TestModel returnObject(){
        return new TestModel("li",18);
    }
}
