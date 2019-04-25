package com.charles.controller;

import com.charles.service.ExcelService;
import com.charles.service.ScheduleServiceHi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author: GanZiB
 * Date: 2019-04-23
 * Time: 14:09
 */
@RestController
public class HiController {
    @Autowired
    private ScheduleServiceHi schedualServiceHi;
    @Autowired
    private ExcelService excelService;

    @GetMapping("hi")
    public String sayHi(@RequestParam String name){
        return schedualServiceHi.sayHiFromClientOne(name);
    }

    @GetMapping("excel-feign")
    public void excelFeign(HttpServletRequest request, HttpServletResponse response) throws Exception {
        buildDownloadResponse(request,response,"feigen.xlsx");
        response.getOutputStream().write(excelService.excelFeign());
    }

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
