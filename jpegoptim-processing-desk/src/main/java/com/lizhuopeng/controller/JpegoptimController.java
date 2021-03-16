package com.lizhuopeng.controller;

import com.lizhuopeng.service.JpegoptimPictureProcessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@RestController
public class JpegoptimController {

    @Autowired
    private JpegoptimPictureProcessService jpegoptimPictureProcessService;


    /**
     * jpegoptim压缩图片api
     * @param picture

     * @return
     * @throws IOException
     */
    @PostMapping("/updatePic")
    public byte[] img(@RequestPart("picture") MultipartFile picture) throws IOException {
        log.info("message come {}",picture);
        return jpegoptimPictureProcessService.processingPic(picture.getBytes());
    }

}
