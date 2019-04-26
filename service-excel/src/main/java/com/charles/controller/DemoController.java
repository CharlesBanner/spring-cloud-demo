package com.charles.controller;


import com.alibaba.excel.support.ExcelTypeEnum;
import com.charles.excel.ExcelDemoUserDTO;
import com.charles.excel.ExcelUser;
import com.charles.excel.ExportTestModel;
import com.charles.exception.ExcelException;
import com.charles.service.DemoUserService;
import com.charles.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
