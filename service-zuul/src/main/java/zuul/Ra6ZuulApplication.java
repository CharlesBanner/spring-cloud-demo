package zuul;


import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableEurekaClient
@SpringCloudApplication
public class Ra6ZuulApplication {


    public static void main(String[] args) {
        SpringApplication.run(Ra6ZuulApplication.class, args);
    }


}
