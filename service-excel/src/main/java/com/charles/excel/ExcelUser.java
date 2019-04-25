package com.charles.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.util.Date;

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
    @ExcelProperty(value = "id",index = 0)
    private int id;
    @ExcelProperty(value = "名称",index = 1)
    private String  name;
    @ExcelProperty(value = "注册时间",index = 3,format = "yyyy/MM/dd")
    private Date loanDate;

    public ExcelUser(int id, String name, Date loanDate) {
        this.id = id;
        this.name = name;
        this.loanDate = loanDate;
    }
}
