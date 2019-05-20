package com.charles.controller;


import com.alibaba.excel.support.ExcelTypeEnum;
import com.charles.excel.ExcelDemoUserDTO;
import com.charles.excel.ExcelUser;
import com.charles.excel.ExportTestModel;
import com.charles.exception.ExcelException;
import com.charles.service.DemoUserService;
import com.charles.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.charles.util.ResponseBuildUtil.buildDownloadResponse;


/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author: GanZiB
 * Date: 2019-04-25
 * Time: 16:25
 */
@RestController
public class DemoController {

    @Autowired
    private DemoUserService demoUserService;

    @GetMapping("/export-excel-demo")
    public void exportExcelUserDemo(HttpServletRequest request,HttpServletResponse response)throws Exception {
        List<ExcelDemoUserDTO> result =  demoUserService.getDemoUserData();
        String fileName = "示例文件111";
        ExcelUtil.writeExcel(response,result,fileName,"test", ExcelTypeEnum.XLSX,ExcelDemoUserDTO.class);
        buildDownloadResponse(request,response,fileName);

    }

    /**
     * 文件（二进制数据）下载
     * @param fileType 文件类型
     * @return
     */
    @RequestMapping("/downloadFile")
    public ResponseEntity<byte[]> downloadFile(String fileType, HttpServletRequest request ){

        System.out.println(request.getParameter("fileType"));
        System.out.println("参数fileType: "+fileType);

        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<byte[]> entity = null;
        InputStream in=null;
        try {
            in=new FileInputStream(new File("d:/myImg/001.png"));

            byte[] bytes = new byte[in.available()];

            String imageName="001.png";

            //处理IE下载文件的中文名称乱码的问题
            String header = request.getHeader("User-Agent").toUpperCase();
            if (header.contains("MSIE") || header.contains("TRIDENT") || header.contains("EDGE")) {
                imageName = URLEncoder.encode(imageName, "utf-8");
                imageName = imageName.replace("+", "%20");    //IE下载文件名空格变+号问题
            } else {
                imageName = new String(imageName.getBytes(), "iso-8859-1");
            }

            in.read(bytes);

            headers.add("Content-Disposition", "attachment;filename="+imageName);

            entity = new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(in!=null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return entity;
    }

    @GetMapping("/export1")
    public void getExportData(HttpServletResponse response){
        List<ExcelUser> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(new ExcelUser(i,"DemoUser"+i,new Date().toString()));
        }
        try {
            ExcelUtil.writeExcel(response,list,"导出测试","没有设定sheet名称", ExcelTypeEnum.XLSX,ExcelUser.class);
        } catch (ExcelException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/upload1")
    public String upload1(@RequestParam(value = "file") MultipartFile file) {
        try {
            List<ExcelDemoUserDTO> list = ExcelUtil.readExcel(file,ExcelDemoUserDTO.class);
            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "失败";
    }

    @PostMapping("/upload-user-demo")
    public String uploadUserDemo(@RequestParam(value = "file") MultipartFile file) {
        try {
            List<ExportTestModel> list = ExcelUtil.readExcel(file,ExportTestModel.class);
            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "失败";
    }


}
