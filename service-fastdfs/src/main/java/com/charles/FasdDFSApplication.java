package com.charles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author: GanZiB
 * Date: 2019-04-26
 * Time: 13:37
 */
@EnableEurekaClient
@SpringBootApplication
public class FasdDFSApplication {

    public static void main(String[] args) {
        SpringApplication.run(FasdDFSApplication.class,args);
    }

}
