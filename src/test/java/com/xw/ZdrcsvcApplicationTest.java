package com.xw;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xw.project.entity.OrgActivitiesPublish;
import com.xw.project.service.disaster.DisContactDetailService;
import com.xw.project.service.organize.OrgActivitiesPublishService;
import com.xw.project.service.organize.impl.OrgActivitiesPublishServiceImpl;
import com.xw.project.service.system.SysActivityService;
import com.xw.project.service.system.impl.SysActivityServiceImpl;
import com.xw.project.vo.SysActivityVo;
import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ZdrcsvcApplicationTest {


    @Test
    public void genTable() throws InterruptedException {

    }

    @Test
    public void getUser(){

    }

}
