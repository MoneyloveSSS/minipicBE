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
    @PostMapping("/jpegoptim/updatePic")
    public byte[] img(@RequestPart("picture") MultipartFile picture,@RequestParam("compressionRatio") Integer compressionRatio) throws IOException {
        log.info("接受图像压缩，压缩率为{}，图像为 {},",compressionRatio,picture);
        return jpegoptimPictureProcessService.processingPic(picture.getBytes(),compressionRatio);
    }

}
