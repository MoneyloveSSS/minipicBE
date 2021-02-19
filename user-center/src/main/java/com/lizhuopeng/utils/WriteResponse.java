package com.lizhuopeng.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lizhuopeng.entities.DataResult;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class WriteResponse {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static void write(HttpServletResponse httpServletResponse, DataResult dataResult) throws IOException {
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        PrintWriter out = httpServletResponse.getWriter();
        out.write(mapper.writeValueAsString(dataResult));
        out.flush();
        out.close();
    }

}
