package com.charles.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author: GanZiB
 * Date: 2019-04-25
 * Time: 15:27
 */
public class ResponseBuildUtil {

    public static void buildDownloadResponse(HttpServletRequest request, HttpServletResponse response, String fileName) throws Exception {

        response.setCharacterEncoding("UTF-8");
        String userAgent = request.getHeader("User-Agent");
        String filename;
        String temp = userAgent == null ? "" : userAgent.toUpperCase();
        if (temp.contains("MSIE") || temp.contains("EDGE") || temp.contains("TRIDENT")) {
            filename = new String(fileName.getBytes("GBK"), "ISO-8859-1");
        } else {
            filename = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
        }
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName=\"" + filename + "\"");
    }

}
