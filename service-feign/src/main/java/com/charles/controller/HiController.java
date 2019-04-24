package com.charles.controller;

import com.charles.service.ScheduleServiceHi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author: GanZiB
 * Date: 2019-04-23
 * Time: 14:09
 */
@RestController
public class HiController {
    @Autowired
    private ScheduleServiceHi schedualServiceHi;

    @GetMapping("hi")
    public String sayHi(@RequestParam String name){
        return schedualServiceHi.sayHiFromClientOne(name);
    }

}
