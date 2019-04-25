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
        excelDemoUserDTOS.add(new ExcelDemoUserDTO(1,"DemoUser1",new Date()));
        excelDemoUserDTOS.add(new ExcelDemoUserDTO(2,"DemoUser2",new Date()));
        excelDemoUserDTOS.add(new ExcelDemoUserDTO(3,"DemoUser3",new Date()));
        excelDemoUserDTOS.add(new ExcelDemoUserDTO(4,"DemoUser4",new Date()));
        excelDemoUserDTOS.add(new ExcelDemoUserDTO(5,"DemoUser5",new Date()));
        excelDemoUserDTOS.add(new ExcelDemoUserDTO(6,"DemoUser6",new Date()));
        excelDemoUserDTOS.add(new ExcelDemoUserDTO(7,"DemoUser7",new Date()));
        return excelDemoUserDTOS;
    }
}
