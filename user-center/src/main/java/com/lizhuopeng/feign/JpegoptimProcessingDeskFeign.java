package com.lizhuopeng.feign;

import com.lizhuopeng.model.TestModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Component
@FeignClient(value = "JPEGOPTIM-PROCESSING-DESK")
public interface JpegoptimProcessingDeskFeign {
    @PostMapping(value = "/updatePic")
    byte[] img(@RequestParam("picture") MultipartFile picture, HttpServletRequest request);

    @GetMapping(value = "/test")
    TestModel returnObject();
}
