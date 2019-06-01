package com.charles.util;

import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.util.CollectionUtils;
import com.charles.dto.ExcelWithOutTempVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author: GanZiB
 * Date: 2019-06-01
 * Time: 13:59
 */
@Slf4j
public class CommonExcelUtil {

    public static ExcelTypeEnum getExcelType(ExcelWithOutTempVO excelWithOutTempVO){
        ExcelTypeEnum excelTypeEnum = null;
        try {
            String excelType = null;
            if (StringUtils.isBlank(excelWithOutTempVO.getExcelType())){
                excelType = ExcelTypeEnum.XLS.getValue();
            }else {
                excelType = excelWithOutTempVO.getExcelType();
            }
            excelTypeEnum = ExcelTypeEnum.valueOf(excelType);
        } catch (Exception e) {
            log.info(String.format("excelType String %s is invalid", excelWithOutTempVO.getExcelType()));
        }
        if (null == excelTypeEnum){
            excelTypeEnum = ExcelTypeEnum.XLS;
        }
        return excelTypeEnum;
    }

    /**
     * 获取格式规则的数据最小行数
     *
     * @return
     */
    public static Integer getMinDataRow(List<Map<String, String>> headers) {
        if (CollectionUtils.isEmpty(headers)) {
            return 0;
        }
        int dataRowNum = headers.stream().mapToInt(header -> getHeaderIntValue(header, "rowEndNum")).max().getAsInt();
        return dataRowNum + 1;
    }

    public static String getFormatStringValue(Object o){
        if (null == o){
            return "--";
        }
        if (o instanceof Date){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
            return sdf.format(o);
        }else{
            return String.valueOf(o);
        }
    }

    /**
     * 获取Map中的int类型数据
     * @param header
     * @param key
     * @return
     */
    public static Integer getHeaderIntValue(Map<String, String> header, String key) {
        String value = header.getOrDefault(key, "0");
        return Integer.valueOf(value);
    }

    /**
     * 获取Map中的String类型数据
     * @param header
     * @param key
     * @return
     */
    public static String getHeaderStringValue(Map<String, String> header, String key) {
        String value = header.getOrDefault(key, "0");
        return String.valueOf(value);
    }

    /**
     * 获取Map中的Boolean类型数据
     * @param header
     * @param key
     * @return
     */
    public static Boolean getHeaderBooleanValue(Map<String, String> header, String key) {
        String value = header.getOrDefault(key, "false");
        return Boolean.valueOf(value);
    }
}
