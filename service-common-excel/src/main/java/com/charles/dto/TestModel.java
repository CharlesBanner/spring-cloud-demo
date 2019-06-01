package com.charles.dto;


import com.charles.util.ExcelDataSort;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.*;


/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author: GanZiB
 * Date: 2019-05-30
 * Time: 15:22
 */
@Data
public class TestModel {
    @ExcelDataSort(sort = "0")
    private String p1;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ExcelDataSort(sort = "1")
    private Date p2;
    @ExcelDataSort(sort = "2")
    private String p3;
    @ExcelDataSort(sort = "3")
    private String p4;
    @ExcelDataSort(sort = "4")
    private String p5;
    @ExcelDataSort(sort = "5")
    private String p6;
    @ExcelDataSort(sort = "6")
    private String p7;
    @ExcelDataSort(sort = "7")
    private String p8;

    public TestModel(String p1, Date p2, String p3, String p4, String p5, String p6, String p7, String p8) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
        this.p5 = p5;
        this.p6 = p6;
        this.p7 = p7;
        this.p8 = p8;
    }

}
