package com.charles.controller;


import com.alibaba.excel.support.ExcelTypeEnum;
import com.charles.excel.ExcelDemoUserDTO;
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
        ExcelUtil.writeExcel(response,result,fileName,"test", ExcelTypeEnum.XLS,ExcelDemoUserDTO.class);
        buildDownloadResponse(request,response,fileName);

    }

    @PostMapping("/upload-user-demo")
    public String uploadUserDemo(@RequestParam(value = "file") MultipartFile file) {
        try {
            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "失败";
    }


}
