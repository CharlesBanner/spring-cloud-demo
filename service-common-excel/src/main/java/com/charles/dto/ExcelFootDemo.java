package com.charles.dto;

import com.charles.util.ExcelFoot;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author: GanZiB
 * Date: 2019-06-01
 * Time: 15:41
 */
@Data
public class ExcelFootDemo {

    @ExcelFoot(cellStartNum = 0, cellEndNum = 1)
    private String rowName;
    @ExcelFoot(cellStartNum = 2, cellEndNum = 2,fontColor = "RED", isMerged = false)
    private String cell1;
    @ExcelFoot(cellStartNum = 3, cellEndNum = 3, isMerged = false)
    private String cell2;
    @ExcelFoot(cellStartNum = 4, cellEndNum = 4, isMerged = false)
    private String cell3;
    @ExcelFoot(cellStartNum = 5, cellEndNum = 5, isMerged = false)
    private String cell4;
    @ExcelFoot(cellStartNum = 6, cellEndNum = 6, isMerged = false)
    private String cell5;
    @ExcelFoot(cellStartNum = 7, cellEndNum = 7, isMerged = false)
    private String cell6;

}
