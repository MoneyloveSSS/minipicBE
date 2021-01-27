package com.lizhuopeng.controller;

import com.lizhuopeng.model.TestModel;
import com.lizhuopeng.service.PictureProcessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@RestController
public class TestController {

    @Autowired
    private PictureProcessService pictureProcessService;


    @GetMapping("/getOne")
    public TestModel returnObject(){
        return new TestModel("li",18);
    }

    @PostMapping("/updatePic")
    public byte[] img(@RequestParam("picture") MultipartFile picture, HttpServletRequest request) throws IOException {
        log.info("message come {}",picture);
        return pictureProcessService.processingPic(picture.getBytes());
    }

}
