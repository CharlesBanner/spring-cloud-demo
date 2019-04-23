package com.charles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author: GanZiB
 * Date: 2019-04-22
 * Time: 17:43
 */
@EnableEurekaClient
@SpringBootApplication
public class ClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class);
    }

}
