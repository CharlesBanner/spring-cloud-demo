package com.charles.service.impl;

import com.charles.excel.ExcelUser;
import com.charles.service.ExcelUserService;
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
 * Time: 14:34
 */
@Service
public class ExcelUserServiceImpl implements ExcelUserService {
    @Override
    public List<ExcelUser> getResult() {
        List<ExcelUser> excelUsers = new ArrayList<>();
        excelUsers.add(new ExcelUser(1,"qwe1",new Date()));
        excelUsers.add(new ExcelUser(2,"qwe2",new Date()));
        excelUsers.add(new ExcelUser(3,"qwe3",new Date()));
        excelUsers.add(new ExcelUser(4,"qwe4",new Date()));
        excelUsers.add(new ExcelUser(5,"qwe5",new Date()));
        return excelUsers;
    }
}
