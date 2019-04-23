package com.charles.controller;

import com.charles.service.HelloService;
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
 * Time: 10:36
 */
@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping(value = "testHi")
    public String hi(@RequestParam String name){
        return helloService.hiService(name);
    }

}
