package com.charles.writer;

import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author: GanZiB
 * Date: 2019-06-01
 * Time: 14:05
 */
public interface CommonExcelBuilder {
    void addContent(List var1, int var2);

    void addContent(List var1, Sheet var2);

    void addContent(List var1, Sheet var2, Table var3);

    void merge(int var1, int var2, int var3, int var4);

    void finish();

    void addHead(List<Map<String, String>> headers);

    void addFoot(List<Map<String, String>> foots);
}
