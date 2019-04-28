package com.charles;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author: GanZiB
 * Date: 2019-04-26
 * Time: 13:37
 */
//导入FdfsClient
@Import(FdfsClientConfig.class)
//解决jmx注册冲突
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
@EnableEurekaClient
@SpringBootApplication
public class FasdDFSApplication {

    public static void main(String[] args) {
        SpringApplication.run(FasdDFSApplication.class,args);
    }

}
