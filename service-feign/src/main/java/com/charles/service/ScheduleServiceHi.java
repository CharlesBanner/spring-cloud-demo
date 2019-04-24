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
 * Date: 2019-04-23
 * Time: 14:07
 */
@Service
@FeignClient(value = "service-hi")
public interface ScheduleServiceHi {

    /***
     * Feign测试接口
     * @param name
     * @return
     */
    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    String sayHiFromClientOne(@RequestParam(value = "name") String name);
}
