package com.charles.service.impl;

import com.charles.excel.ExcelDemoUserDTO;
import com.charles.service.DemoUserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author: GanZiB
 * Date: 2019-04-25
 * Time: 16:26
 */
@Service
public class DemoUserServiceImpl implements DemoUserService {
    @Override
    public List<ExcelDemoUserDTO> getDemoUserData() {
        List<ExcelDemoUserDTO> excelDemoUserDTOS = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            excelDemoUserDTOS.add(new ExcelDemoUserDTO(i,"DemoUser"+i,new Date()));
        }
        return excelDemoUserDTOS;
    }
}
