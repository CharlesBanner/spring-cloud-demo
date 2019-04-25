package com.charles.service;

import com.charles.excel.ExcelUser;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author: GanZiB
 * Date: 2019-04-25
 * Time: 14:29
 */
public interface ExcelUserService {

    /**
     * 获取用户列表
     * @return
     */
    List<ExcelUser> getResult();
}
