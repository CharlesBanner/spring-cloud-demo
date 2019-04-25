package com.charles.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author: GanZiB
 * Date: 2019-04-25
 * Time: 16:02
 */
@Service
@FeignClient(value = "service-excel")
public interface ExcelService {

    /***
     * Feign测试接口
     * @return
     */
    @RequestMapping(value = "/excel-export",method = RequestMethod.GET)
    byte[] excelFeign();
}
