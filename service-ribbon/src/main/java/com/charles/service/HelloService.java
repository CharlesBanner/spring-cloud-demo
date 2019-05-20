package com.charles.service;

import com.bbd.sc.ent.common.entity.excel.ExcelDemoUserDTO;
import com.bbd.sc.ent.common.entity.vo.req.ReqExportExcelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author: GanZiB
 * Date: 2019-04-23
 * Time: 9:55
 */
@Service
public class HelloService {

    @Autowired
    private RestTemplate restTemplate;

    public String hiService(String name){
        return restTemplate.getForObject("http://SERVICE-HI/hi?name="+name,String.class);
    }

    public void excelService(HttpServletResponse response){
        Map<String,Object> map = new HashMap<>(3);

        ReqExportExcelVO reqExportExcelVO = new ReqExportExcelVO();
        reqExportExcelVO.setClassName(ExcelDemoUserDTO.class.getName());
        List<ExcelDemoUserDTO> excelDemoUserDTOS = new ArrayList<>();
        int count = 1000;
        for (int i = 0; i < count; i++) {
            excelDemoUserDTOS.add(new ExcelDemoUserDTO(i, "DemoUser" + i, new Date()));
        }
//        reqExportExcelVO.setDataList(excelDemoUserDTOS);
        reqExportExcelVO.setFileName("testFile");
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_OCTET_STREAM.toString());
        HttpEntity<String> formEntity = new HttpEntity<String>(reqExportExcelVO.toString(), headers);
        restTemplate.postForObject("http://COMMON-EXCEL/api/v1.0/excel/export",formEntity, ServletOutputStream.class);
//        restTemplate.getForObject("http://COMMON-EXCEL/api/v1.0/export-user-demo",String.class);
    }

}
