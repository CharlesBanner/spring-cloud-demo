package com.charles.controller;

import com.charles.dto.ExcelWithOutTempVO;
import com.charles.util.CommonExcelUtil;
import com.charles.writer.CommonExcelWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author: GanZiB
 * Date: 2019-06-01
 * Time: 13:18
 */
@Slf4j
@RestController
public class ExcelController {

    @PostMapping("/easyExport")
    public void exportController(HttpServletRequest request, HttpServletResponse response, @RequestBody ExcelWithOutTempVO excelVO) throws FileNotFoundException {
        FileOutputStream fileOutputStream = new FileOutputStream(new File("D:test1.xls"));
        if(null != excelVO && !CollectionUtils.isEmpty(excelVO.getHeaders())){
            try {
                CommonExcelWriter excelWriter  = new CommonExcelWriter(fileOutputStream, CommonExcelUtil.getExcelType(excelVO),true);
                excelWriter.addHead(excelVO.getHeaders());
                excelWriter.writeListMap(excelVO.getDataList(),CommonExcelUtil.getMinDataRow(excelVO.getDataList()));
                excelWriter.addFoot(excelVO.getFooter());
                excelWriter.finish();
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
