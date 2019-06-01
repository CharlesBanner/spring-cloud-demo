package com.charles.util;

import com.alibaba.fastjson.JSONObject;
import com.charles.dto.ExcelFootDemo;
import com.charles.dto.ExcelHeaderDemo;
import com.charles.dto.ExcelWithOutTempVO;
import com.charles.dto.TestModel;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author: GanZiB
 * Date: 2019-05-30
 * Time: 19:47
 */
public class BuildExcelDataUtil {

    /**
     * 根据注解生成Excel Header的Json字符串 作为excel导出的参数
     * @param headerDemo 类属性上有添加 ExcelHeader 的对象
     * @return
     */
    public static List<Map<String, String>> buildHeaders(Object headerDemo) {
        List<Map<String, String>> data = new ArrayList<>();
        Field[] declaredFields = headerDemo.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            Map<String, String> fieldMap = new HashMap<>(5);
            if (field.isAnnotationPresent(ExcelHeader.class)) {
                ExcelHeader annotation = field.getAnnotation(ExcelHeader.class);
                try {
                    field.setAccessible(true);
                    fieldMap.put("rowStartNum", String.valueOf(annotation.rowStartNum()));
                    fieldMap.put("rowEndNum", String.valueOf(annotation.rowEndNum()));
                    fieldMap.put("cellStartNum", String.valueOf(annotation.cellStartNum()));
                    fieldMap.put("cellEndNum", String.valueOf(annotation.cellEndNum()));
                    fieldMap.put("isMerged", String.valueOf(annotation.isMerged()));
                    fieldMap.put("cellValue", String.valueOf(field.get(headerDemo)));
                    fieldMap.put("fontColor", String.valueOf(annotation.fontColor()));
                    data.add(fieldMap);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
       return data;
    }

    public static List<Map<String, String>> buildFoots(Object footDemo) {
        List<Map<String, String>> data = new ArrayList<>();
        Field[] declaredFields = footDemo.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            Map<String, String> fieldMap = new HashMap<>(5);
            if (field.isAnnotationPresent(ExcelFoot.class)) {
                ExcelFoot annotation = field.getAnnotation(ExcelFoot.class);
                try {
                    field.setAccessible(true);
                    fieldMap.put("cellStartNum", String.valueOf(annotation.cellStartNum()));
                    fieldMap.put("cellEndNum", String.valueOf(annotation.cellEndNum()));
                    fieldMap.put("isMerged", String.valueOf(annotation.isMerged()));
                    fieldMap.put("cellValue", String.valueOf(field.get(footDemo)));
                    fieldMap.put("fontColor", String.valueOf(annotation.fontColor()));
                    data.add(fieldMap);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return data;
    }

    /**
     *
     * @param excelDataList
     * @return
     */
    public static<T> List<Map<String, String>> buildExcelData(List<T> excelDataList) {
        List<Map<String, String>> excelData = new ArrayList<>(excelDataList.size());
        for (T dataObject : excelDataList) {
            Field[] declaredFields = dataObject.getClass().getDeclaredFields();
            Map<String, String> fieldMap = new HashMap<>(5);
            for (Field field : declaredFields) {
                if (field.isAnnotationPresent(ExcelDataSort.class)) {
                    ExcelDataSort annotation = field.getAnnotation(ExcelDataSort.class);
                    try {
                        field.setAccessible(true);
                        Object o = field.get(dataObject);
                        fieldMap.put(annotation.sort(),getFormatStringValue(o));

                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            }
            excelData.add(fieldMap);
        }
        return excelData;
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
    /**
     * 获取一个测试生成excel文件表头对象
     * @return
     */
    public static ExcelHeaderDemo getHeaderDemo(){
        ExcelHeaderDemo excelHeaderDemo = new ExcelHeaderDemo();
        excelHeaderDemo.setTableName("征信系统用户登录情况统计表");
        excelHeaderDemo.setReportDate("报告日期:2019-05");
        excelHeaderDemo.setUnitType("单位类型");
        excelHeaderDemo.setUnitTypeValue("测试单位");
        excelHeaderDemo.setUserType("用户类型");
        excelHeaderDemo.setUserTypeValue("测试用户");
        excelHeaderDemo.setUserNums("用户数量");
        excelHeaderDemo.setLoginNums("登录系统次数");
        excelHeaderDemo.setUnitCode("单位编号");
        excelHeaderDemo.setUnitName("单位名称");
        excelHeaderDemo.setTotalUserNum("累积用户数");
        excelHeaderDemo.setYearUserAmount("本年新增用户数");
        excelHeaderDemo.setMonthUserAmount("本月新增用户数");
        excelHeaderDemo.setLoginNum("累计登录次数");
        excelHeaderDemo.setYearLoginNum("本年登录次数");
        excelHeaderDemo.setMonthLoginNum("本月登录次数");
        return excelHeaderDemo;
    }

    public static ExcelFootDemo getFootsDemo(){
        ExcelFootDemo excelFootDemo = new ExcelFootDemo();
        excelFootDemo.setRowName("合计");
        excelFootDemo.setCell1("9");
        excelFootDemo.setCell2("12");
        excelFootDemo.setCell3("15");
        excelFootDemo.setCell4("18");
        excelFootDemo.setCell5("21");
        excelFootDemo.setCell6("26");
        return excelFootDemo;
    }

    public static void main(String[] args) {
        List<Map<String, String>> maps = buildHeaders(getHeaderDemo());
        ExcelWithOutTempVO excelWithOutTempVO = new ExcelWithOutTempVO();
        excelWithOutTempVO.setHeaders(maps);
        excelWithOutTempVO.setFooter(buildFoots(getFootsDemo()));
        List<TestModel> testModels = new ArrayList<>(10);
        testModels.add(new TestModel("1", new Date(), "3", "4", "5", "6", "7", "8"));
        testModels.add(new TestModel("1", new Date(), "3", "4", "5", "6", "7", "8"));
        testModels.add(new TestModel("1", new Date(), "3", "4", "5", "6", "7", "8"));
        List<Map<String, String>> data = buildExcelData(testModels);
        excelWithOutTempVO.setDataList(data);
        System.out.println(JSONObject.toJSONString(excelWithOutTempVO));
    }
}
