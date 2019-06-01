package com.charles.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author: GanZiB
 * Date: 2019-05-30
 * Time: 16:57
 */
@Data
public class ExcelWithOutTempVO {

    /**
     * excel表头(有合并需求) 需放入rowStartNum:起始行号,cellStartNum:起始列号,rowEndNum:起始行号,cellEndNum:起始列号,cellValue:单元格内容
     */
    private List<Map<String,String>> headers;

    /**
     * excel表格中的规范数据
     */
    private List<Map<String,String>> dataList;

    /**
     * excel表格合计数据
     */
    private List<Map<String,String>> footer;

    /**
     * excel文件格式 xls xlsx
     */
    private String excelType;

}
