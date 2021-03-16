package com.lizhuopeng.feign;

import com.lizhuopeng.model.TestModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;


@Component
@FeignClient(value = "JPEGOPTIM-PROCESSING-DESK")
public interface JpegoptimProcessingDeskFeign {
    @PostMapping(value = "/updatePic",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    byte[] img(@RequestPart("picture") MultipartFile picture);

    @GetMapping(value = "/test")
    TestModel returnObject();
}
