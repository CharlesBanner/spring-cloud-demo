package com.charles.writer;

import com.alibaba.excel.context.WriteContext;
import com.alibaba.excel.event.WriteHandler;
import com.alibaba.excel.exception.ExcelGenerateException;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.ExcelColumnProperty;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.util.CollectionUtils;
import com.alibaba.excel.util.POITempFile;
import com.alibaba.excel.util.TypeUtil;
import com.alibaba.excel.util.WorkBookUtil;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.beans.BeanMap;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.charles.util.BuildExcelDataUtil.getHeaderIntValue;
import static com.charles.util.CommonExcelUtil.getHeaderBooleanValue;
import static com.charles.util.CommonExcelUtil.getHeaderStringValue;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author: GanZiB
 * Date: 2019-06-01
 * Time: 13:39
 */
@Slf4j
public class CommonExcelBuilderImpl implements CommonExcelBuilder {

    private WriteContext context;

    public CommonExcelBuilderImpl(InputStream templateInputStream, OutputStream out, ExcelTypeEnum excelType, boolean needHead, WriteHandler writeHandler) {
        try {
            POITempFile.createPOIFilesDirectory();
            this.context = new WriteContext(templateInputStream, out, excelType, needHead, writeHandler);
            if (null ==this.context.getCurrentSheet()){
                context.setCurrentSheet(context.getWorkbook().createSheet());
            }
        } catch (Exception var7) {
            throw new RuntimeException(var7);
        }
    }

    @Override
    public void addContent(List data, int startRow) {
        if (!CollectionUtils.isEmpty(data)) {
            int rowNum = this.context.getCurrentSheet().getLastRowNum();
            if (rowNum == 0) {
                Row row = this.context.getCurrentSheet().getRow(0);
                if (row == null && (this.context.getExcelHeadProperty() == null || !this.context.needHead())) {
                    rowNum = -1;
                }
            }

            if (rowNum < startRow) {
                rowNum = startRow;
            }

            for(int i = 0; i < data.size(); ++i) {
                int n = i + rowNum + 1;
                this.addOneRowOfDataToExcel(data.get(i), n);
            }

        }
    }

    @Override
    public void addContent(List data, Sheet sheetParam) {
        this.context.currentSheet(sheetParam);
        this.addContent(data, sheetParam.getStartRow());
    }

    @Override
    public void addContent(List data, Sheet sheetParam, Table table) {
        this.context.currentSheet(sheetParam);
        this.context.currentTable(table);
        this.addContent(data, sheetParam.getStartRow());
    }

    @Override
    public void merge(int firstRow, int lastRow, int firstCol, int lastCol) {
        CellRangeAddress cra = new CellRangeAddress(firstRow, lastRow, firstCol, lastCol);
        this.context.getCurrentSheet().addMergedRegion(cra);
    }

    @Override
    public void finish() {
        try {
            this.context.getWorkbook().write(this.context.getOutputStream());
            this.context.getWorkbook().close();
        } catch (IOException var2) {
            throw new ExcelGenerateException("IO error", var2);
        }
    }

    @Override
    public void addHead(List<Map<String, String>> headers) {
        for (Map<String, String> header : headers) {
            /**/
            margeHead(header);
            Row headRow = getHeadRow(header);
            CellStyle headCellStyle = getHeadCellStyle(header);
            WorkBookUtil.createCell(headRow, getHeaderIntValue(header, "cellStartNum"),
                    headCellStyle, getHeaderStringValue(header, "cellValue"));
        }
    }

    @Override
    public void addFoot(List<Map<String, String>> foots) {
        int lastRowNum = this.context.getCurrentSheet().getLastRowNum()+1;
        for (Map<String, String> foot : foots) {
            if (getHeaderBooleanValue(foot, "isMerged")){
                CellRangeAddress cellRangeAddress = new CellRangeAddress(lastRowNum,
                        lastRowNum,
                        getHeaderIntValue(foot, "cellStartNum"),
                        getHeaderIntValue(foot, "cellEndNum"));
                this.context.getCurrentSheet().addMergedRegion(cellRangeAddress);
            }
                CellStyle footCellStyle = getHeadCellStyle(foot);
                Row row = this.context.getCurrentSheet().getRow(lastRowNum);
                if (row == null) {
                    row = this.context.getCurrentSheet().createRow(lastRowNum);
                }
                WorkBookUtil.createCell(row, getHeaderIntValue(foot, "cellStartNum"),
                        footCellStyle, getHeaderStringValue(foot, "cellValue"));

        }

    }

    private void margeHead(Map<String, String> header){
        if (getHeaderBooleanValue(header, "isMerged")){
            CellRangeAddress cellRangeAddress = new CellRangeAddress(getHeaderIntValue(header, "rowStartNum"),
                    getHeaderIntValue(header, "rowEndNum"),
                    getHeaderIntValue(header, "cellStartNum"),
                    getHeaderIntValue(header, "cellEndNum"));
            this.context.getCurrentSheet().addMergedRegion(cellRangeAddress);
            // 下边框
            RegionUtil.setBorderBottom(BorderStyle.THIN, cellRangeAddress, this.context.getCurrentSheet());
            // 左边框
            RegionUtil.setBorderLeft(BorderStyle.THIN, cellRangeAddress, this.context.getCurrentSheet());
            // 有边框
            RegionUtil.setBorderRight(BorderStyle.THIN, cellRangeAddress, this.context.getCurrentSheet());
            // 上边框
            RegionUtil.setBorderTop(BorderStyle.THIN, cellRangeAddress, this.context.getCurrentSheet());
        }
    }

    private CellStyle getHeadCellStyle(Map<String, String> header){
        String fontColor = getHeaderStringValue(header, "fontColor");
        HSSFColor.HSSFColorPredefined colorPredefined = null;
        try {
            colorPredefined = HSSFColor.HSSFColorPredefined.valueOf(fontColor);
        } catch (Exception e) {
            log.info(String.format("FontColor String %s is invalid!", fontColor));
        }
        if (null == colorPredefined){
            colorPredefined = HSSFColor.HSSFColorPredefined.BLACK;
        }
        CellStyle cellStyle = getDefaultStyle(true,colorPredefined.getColor().getIndex());
        cellStyle.setWrapText(true);
        return cellStyle;
    }

    private Row getHeadRow(Map<String, String> header){
        Row row = this.context.getCurrentSheet().getRow(getHeaderIntValue(header, "rowStartNum"));
        if (row == null) {
            row = this.context.getCurrentSheet().createRow(getHeaderIntValue(header, "rowStartNum"));
        }
        return row;
    }

    private CellStyle getDefaultStyle() {
        return getDefaultStyle(false,HSSFColor.HSSFColorPredefined.BLACK.getIndex());
    }

    /**
     * 设置单元格样式
     *
     * @return
     */
    private CellStyle getDefaultStyle(Boolean isHead,short colorIndex) {

        Font font = this.context.getWorkbook().createFont();
        font.setFontName("宋体");
        font.setColor(colorIndex);
        if (isHead){
            font.setBold(true);
            font.setFontHeightInPoints((short) 14);
        }else {
            font.setBold(false);
            font.setFontHeightInPoints((short) 10);
        }
        CellStyle cellStyle =  this.context.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setLocked(true);
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        // 边框，居中
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        /*自动换行*/
        cellStyle.setWrapText(true);
        return cellStyle;
    }

    private void addBasicTypeToExcel(List<Object> oneRowData, Row row) {
        if (!CollectionUtils.isEmpty(oneRowData)) {
            for(int i = 0; i < oneRowData.size(); ++i) {
                Object cellValue = oneRowData.get(i);
                Cell cell = WorkBookUtil.createCell(row, i, this.context.getCurrentContentStyle(), cellValue, TypeUtil.isNum(cellValue));
                if (null != this.context.getAfterWriteHandler()) {
                    this.context.getAfterWriteHandler().cell(i, cell);
                }
            }

        }
    }

    private void addMapToExcel(Map<String,String> oneRowData, Row row) {
        if (!CollectionUtils.isEmpty(oneRowData)) {
            Map<Integer, String> currentRow = getCurrentRowData(oneRowData);
            if (null != currentRow) {
                Set<Integer> integers = currentRow.keySet();
                for (int j = 0; j < integers.size(); j++) {
                    //创建Cell
                    Cell cell = row.createCell(j);
                    CellStyle cellStyle = getDefaultStyle();
                    cell.setCellStyle(cellStyle);
                    //设置样式
                    Object value = currentRow.get(j);
                    setCellValue(cell, value);
                }
            }
        }
    }

    /**
     * 设置Cell值
     */
    private void setCellValue(Cell cell, Object value) {
        if (cell != null) {
            //设置默认值
            cell.setCellValue("--");
            if (value != null) {
                if (value instanceof Integer) {
                    cell.setCellValue((Integer) value);
                } else if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                } else if (value instanceof Date){
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
                    cell.setCellValue(sdf.format(value));
                }else {
                    cell.setCellValue(value.toString());
                }
            }
        }
    }

    /**
     * 构建行数据
     * @param stringCurrentRow
     * @return
     */
    private Map<Integer, String> getCurrentRowData(Map<String, String> stringCurrentRow) {
        Set<String> keySet = stringCurrentRow.keySet();
        Map<Integer, String> currentRow = new HashMap<>(keySet.size());
        if (CollectionUtils.isEmpty(keySet)) {
            return null;
        } else {
            keySet.forEach(s -> currentRow.put(Integer.valueOf(s), stringCurrentRow.get(s)));
        }
        return currentRow;
    }

    private void addJavaObjectToExcel(Object oneRowData, Row row) {
        int i = 0;
        BeanMap beanMap = BeanMap.create(oneRowData);

        for(Iterator var5 = this.context.getExcelHeadProperty().getColumnPropertyList().iterator(); var5.hasNext(); ++i) {
            ExcelColumnProperty excelHeadProperty = (ExcelColumnProperty)var5.next();
            BaseRowModel baseRowModel = (BaseRowModel)oneRowData;
            String cellValue = TypeUtil.getFieldStringValue(beanMap, excelHeadProperty.getField().getName(), excelHeadProperty.getFormat());
            CellStyle cellStyle = baseRowModel.getStyle(i) != null ? baseRowModel.getStyle(i) : this.context.getCurrentContentStyle();
            Cell cell = WorkBookUtil.createCell(row, i, cellStyle, cellValue, TypeUtil.isNum(excelHeadProperty.getField()));
            if (null != this.context.getAfterWriteHandler()) {
                this.context.getAfterWriteHandler().cell(i, cell);
            }
        }

    }

    private void addOneRowOfDataToExcel(Object oneRowData, int n) {
        Row row = WorkBookUtil.createRow(this.context.getCurrentSheet(), n);
        if (null != this.context.getAfterWriteHandler()) {
            this.context.getAfterWriteHandler().row(n, row);
        }

        if (oneRowData instanceof List) {
            this.addBasicTypeToExcel((List)oneRowData, row);
        }if (oneRowData instanceof Map){
            this.addMapToExcel((Map)oneRowData,row);
        }else {
            this.addJavaObjectToExcel(oneRowData, row);
        }

    }
}
