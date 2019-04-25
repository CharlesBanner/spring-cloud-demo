package com.charles.service;


import com.charles.excel.ExcelDemoUserDTO;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author: GanZiB
 * Date: 2019-04-25
 * Time: 16:26
 */
public interface DemoUserService {
    /**
     * 获取数据
     * @return
     */
    List<ExcelDemoUserDTO> getDemoUserData();
}
