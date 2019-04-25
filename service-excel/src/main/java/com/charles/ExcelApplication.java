package com.charles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author: GanZiB
 * Date: 2019-04-25
 * Time: 14:22
 */

@EnableEurekaClient
@SpringBootApplication
public class ExcelApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExcelApplication.class);
    }

}
