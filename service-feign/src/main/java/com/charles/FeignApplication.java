package com.charles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author: GanZiB
 * Date: 2019-04-23
 * Time: 13:52
 */
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class FeignApplication {

    public static void main(String[] args) {
        SpringApplication.run( FeignApplication.class);
    }
}
