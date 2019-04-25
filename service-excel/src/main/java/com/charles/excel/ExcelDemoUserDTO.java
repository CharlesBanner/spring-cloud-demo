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
 * Time: 16:33
 */
public class ExcelDemoUserDTO extends BaseRowModel {

    @ExcelProperty(value = "id",index = 0)
    private int id;
    @ExcelProperty(value = "名称",index = 1)
    private String  name;
    @ExcelProperty(value = "注册时间",index = 3,format = "yyyy/MM/dd")
    private Date registerDate;

    public ExcelDemoUserDTO(int id, String name, Date registerDate) {
        this.id = id;
        this.name = name;
        this.registerDate = registerDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    @Override
    public String toString() {
        return "ExcelDemoUserDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", registerDate=" + registerDate +
                '}';
    }
}
