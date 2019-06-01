package com.charles.dto;

import com.charles.util.ExcelHeader;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author: GanZiB
 * Date: 2019-05-30
 * Time: 19:09
 */
@Data
public class ExcelHeaderDemo {
    @ExcelHeader(rowStartNum = 0, rowEndNum = 2, cellStartNum = 0, cellEndNum = 7)
    private String tableName;

    @ExcelHeader(rowStartNum = 3, rowEndNum = 3, cellStartNum = 0, cellEndNum = 7,fontColor = "RED")
    private String reportDate;

    @ExcelHeader(rowStartNum = 4, rowEndNum = 4, cellStartNum = 1, cellEndNum = 1, isMerged = false)
    private String unitType;

    @ExcelHeader(rowStartNum = 4, rowEndNum = 4, cellStartNum = 2, cellEndNum = 2, isMerged = false,fontColor = "RED")
    private String unitTypeValue;

    @ExcelHeader(rowStartNum = 4, rowEndNum = 4, cellStartNum = 4, cellEndNum = 4, isMerged = false)
    private String userType;

    @ExcelHeader(rowStartNum = 4, rowEndNum = 4, cellStartNum = 5, cellEndNum = 5, isMerged = false,fontColor = "RED")
    private String userTypeValue;

    @ExcelHeader(rowStartNum = 5, rowEndNum = 5, cellStartNum = 2, cellEndNum = 4)
    private String userNums;

    @ExcelHeader(rowStartNum = 5, rowEndNum = 5, cellStartNum = 5, cellEndNum = 7)
    private String loginNums;

    @ExcelHeader(rowStartNum = 5, rowEndNum = 6, cellStartNum = 0, cellEndNum = 0, isMerged = true)
    private String unitCode;

    @ExcelHeader(rowStartNum = 5, rowEndNum = 6, cellStartNum = 1, cellEndNum = 1, isMerged = true)
    private String unitName;

    @ExcelHeader(rowStartNum = 6, rowEndNum = 6, cellStartNum = 2, cellEndNum = 2, isMerged = false)
    private String totalUserNum;

    @ExcelHeader(rowStartNum = 6, rowEndNum = 6, cellStartNum = 3, cellEndNum = 3, isMerged = false)
    private String yearUserAmount;

    @ExcelHeader(rowStartNum = 6, rowEndNum = 6, cellStartNum = 4, cellEndNum = 4, isMerged = false)
    private String monthUserAmount;

    @ExcelHeader(rowStartNum = 6, rowEndNum = 6, cellStartNum = 5, cellEndNum = 5, isMerged = false)
    private String loginNum;

    @ExcelHeader(rowStartNum = 6, rowEndNum = 6, cellStartNum = 6, cellEndNum = 6, isMerged = false)
    private String yearLoginNum;

    @ExcelHeader(rowStartNum = 6, rowEndNum = 6, cellStartNum = 7, cellEndNum = 7, isMerged = false)
    private String monthLoginNum;
}
