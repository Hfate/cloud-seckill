package com.hcq.seckill.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import static com.hcq.seckill.constant.GlobalConstant.COOKIE_NAME_TOKEN;
import static com.hcq.seckill.constant.GlobalConstant.MAX_AGE;

/**
 * @author admin
 */
public class HttpUtil {
    /**
     * 向前端返回文件
     *
     * @param response
     * @param bytes
     * @param contentType
     * @param fileName
     * @throws IOException
     */
    public static void renderFile(HttpServletResponse response, byte[] bytes, String contentType, String fileName) throws IOException {
        response.setContentType(contentType);
        if (StringUtils.isNotEmpty(fileName)) {
            response.setHeader("Content-disposition", "attachment;filename=\"" + new String(fileName.getBytes("GBK"), StandardCharsets.ISO_8859_1) + "\"");
            response.setContentLength(bytes.length);
        }
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            out.write(bytes);
        } finally {
            IOUtils.closeQuietly(out);
        }
    }

    /**
     * 向前端返回文件
     *
     * @param response
     * @param bytes
     * @param fileName
     * @throws IOException
     */
    public static void renderExcelFile(HttpServletResponse response, byte[] bytes, String fileName) throws IOException {
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        if (StringUtils.isNotEmpty(fileName)) {
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
            response.setContentLength(bytes.length);
        }
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            out.write(bytes);
        } finally {
            IOUtils.closeQuietly(out);
        }
    }

    /**
     * 向前端返回文件
     *
     * @param response
     * @param inputStream
     * @param contentType
     * @throws IOException
     */
    public static void renderFile(HttpServletResponse response, InputStream inputStream, String contentType) throws IOException {
        response.setContentType(contentType);
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            IOUtils.copy(inputStream, out);
        } finally {
            IOUtils.closeQuietly(out);
        }
    }

    /**
     * 向前端返回文件
     *
     * @param response
     * @param contentType
     * @throws IOException
     */
    public static void renderFile(HttpServletResponse response, byte[] bytes, String contentType) throws IOException {
        response.setContentType(contentType);
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            out.write(bytes);
        } finally {
            IOUtils.closeQuietly(out);
        }
    }

    /**
     * 向前端返回json串
     *
     * @param response
     * @param object
     */
    public static void renderJSON(HttpServletResponse response, Object object) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer;
        try {
            writer = response.getWriter();
            writer.write(JSON.toJSONString(object, SerializerFeature.WriteMapNullValue));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void writeTokenCookie(String token, HttpServletResponse response) {
        writeCookie("/", MAX_AGE, COOKIE_NAME_TOKEN, token, response);
    }

    public static void writeCookie(String path, int maxAge, String name, String value, HttpServletResponse response) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath(path);
        response.addCookie(cookie);
    }

}
