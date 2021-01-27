package com.lizhuopeng.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class PictureProcessService {

    /**
     * 返回处理图片
     * @param image
     * @return
     * @throws IOException
     */
    public byte[] processingPic(byte[] image) throws IOException {
        List<String> commandLine = getCommandLine();
        return execute(commandLine,image);
    }


    /**
     * 得到jpegoptim命令行
     * @return
     */
    public List<String> getCommandLine(){
        List<String> commandLine=new ArrayList<>();
        commandLine.add("wsl");
        commandLine.add("jpegoptim");
        commandLine.add("-m90");
        commandLine.add("--stdout");
        commandLine.add("--stdin");
        return commandLine;
    }


    /**
     * 与jpegoptim交互得到处理结果
     * @param commandLine
     * @param image
     * @return
     * @throws IOException
     */
    public byte[] execute(List<String> commandLine, byte[] image) throws IOException {

        ProcessBuilder processBuilder = new ProcessBuilder(commandLine);

        final Process process = processBuilder.start();

        OutputStream stdin = process.getOutputStream ();
        final InputStream stderr = process.getErrorStream ();
        final InputStream stdout = process.getInputStream ();


        final ByteArrayOutputStream bos = new ByteArrayOutputStream();

        Thread outputT = new Thread(() -> {
            byte[] buf = new byte[1024];
            int len;
            try {
                while ((len = stdout.read(buf)) != -1) {
                    // process byte buffer
                    bos.write(buf, 0, len);
                    bos.flush();
                }
            } catch (IOException e) {
                log.warn("IOException in PictureProcessService:execute:outputT");
            }
        });

        outputT.start();

        Thread errorT = new Thread(() -> {
            byte[] buf = new byte[1024];
            int len;
            try {
                while ((len = stderr.read(buf)) != -1) {
                    System.err.write(buf, 0, len);
                    System.err.flush();
                }

            } catch (IOException e) {
                log.warn("IOException in PictureProcessService:execute:errorT");
            }

        });

        errorT.start();

        stdin.write(image);
        stdin.flush();

        stdin.close();

        try {
            process.waitFor();

            errorT.join();
            outputT.join();
        } catch (InterruptedException e1) {
            log.warn("InterruptedException in PictureProcessService:execute:waitting for result");
        }

        stdin.close();
        stderr.close();
        stdout.close();

        log.info("压缩图片完成");
        return bos.toByteArray();

    }
}
