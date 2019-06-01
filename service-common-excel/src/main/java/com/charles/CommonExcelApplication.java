package com.charles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author: GanZiB
 * Date: 2019-06-01
 * Time: 13:11
 */
@EnableEurekaClient
@SpringBootApplication
public class CommonExcelApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonExcelApplication.class,args);
    }

}
