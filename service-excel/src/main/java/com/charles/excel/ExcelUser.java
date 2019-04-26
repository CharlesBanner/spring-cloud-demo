package com.charles.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author: GanZiB
 * Date: 2019-04-25
 * Time: 14:29
 */
@Data
public class ExcelUser extends BaseRowModel {
    private int id;
    @ExcelProperty(value = "名称",index =0)
    private String  name;
    @ExcelProperty(value = "注册时间",index = 1)
    private String loanDate;

    public ExcelUser(int id, String name, String loanDate) {
        this.id = id;
        this.name = name;
        this.loanDate = loanDate;
    }

    public ExcelUser() {
    }
}
