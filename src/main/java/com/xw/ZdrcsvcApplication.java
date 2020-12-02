package com.xw;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = {
        "com.xw.common",
        "com.xw.system",
        "com.xw.project",
        "com.xw"}, exclude = {
        SecurityAutoConfiguration.class
})
@MapperScan({"com.xw.common.mapper", "com.xw.system.mapper", "com.xw.project.mapper"})
@EnableSwagger2
@EnableAsync
@ConfigurationProperties(prefix = "spring.datasource.activiti")
public class ZdrcsvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZdrcsvcApplication.class, args);
    }
}