package com.charles.writer;

import com.alibaba.excel.event.WriteHandler;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.ExcelBuilder;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import static com.charles.util.BuildExcelDataUtil.getHeaderIntValue;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author: GanZiB
 * Date: 2019-06-01
 * Time: 13:45
 */
public class CommonExcelWriter {
    private CommonExcelBuilder excelBuilder;

    public CommonExcelWriter(OutputStream outputStream, ExcelTypeEnum typeEnum) {
        this(outputStream, typeEnum, true);
    }

    public CommonExcelWriter(OutputStream outputStream, ExcelTypeEnum typeEnum, boolean needHead) {

        this.excelBuilder = new CommonExcelBuilderImpl((InputStream)null, outputStream, typeEnum, needHead, (WriteHandler)null);
    }

    public CommonExcelWriter(InputStream templateInputStream, OutputStream outputStream, ExcelTypeEnum typeEnum, Boolean needHead) {
        this.excelBuilder = new CommonExcelBuilderImpl(templateInputStream, outputStream, typeEnum, needHead, (WriteHandler)null);
    }

    public CommonExcelWriter(InputStream templateInputStream, OutputStream outputStream, ExcelTypeEnum typeEnum, Boolean needHead, WriteHandler writeHandler) {
        this.excelBuilder = new CommonExcelBuilderImpl(templateInputStream, outputStream, typeEnum, needHead, writeHandler);
    }

    public CommonExcelWriter write1(List<List<Object>> data, Sheet sheet) {
        this.excelBuilder.addContent(data, sheet);
        return this;
    }

    public CommonExcelWriter write0(List<List<String>> data, Sheet sheet) {
        this.excelBuilder.addContent(data, sheet);
        return this;
    }

    public CommonExcelWriter write0(List<List<String>> data, Sheet sheet, Table table) {
        this.excelBuilder.addContent(data, sheet, table);
        return this;
    }
    public CommonExcelWriter merge(int firstRow, int lastRow, int firstCol, int lastCol) {
        this.excelBuilder.merge(firstRow, lastRow, firstCol, lastCol);
        return this;
    }

    public CommonExcelWriter write1(List<List<Object>> data, Sheet sheet, Table table) {
        this.excelBuilder.addContent(data, sheet, table);
        return this;
    }

    public void finish() {
        this.excelBuilder.finish();
    }

    public void addHead(List<Map<String, String>> headers) {
        this.excelBuilder.addHead(headers);
    }

    public void writeListMap(List<Map<String, String>> dataList, Integer minDataRow) {
        this.excelBuilder.addContent(dataList,minDataRow);
    }

    public void addFoot(List<Map<String,String>> footer) {
        this.excelBuilder.addFoot(footer);
    }
}
