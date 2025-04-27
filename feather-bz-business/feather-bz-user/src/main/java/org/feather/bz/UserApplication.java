package org.feather.bz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = "org.feather.bz")
@MapperScan("org.feather.bz.mapper")
public class UserApplication implements ApplicationListener<ApplicationStartedEvent> {

    @Autowired
    private ConfigurableApplicationContext applicationContext;


    public static void main(String[] args) {

        SpringApplication.run(UserApplication.class, args);
    }

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        // 从配置中获取真实端口（支持动态端口和自定义配置）
        String port = applicationContext.getEnvironment()
                .getProperty("server.port", "8080"); // 默认8080

        System.out.println("\n-------------------- Swagger 地址 --------------------");
        System.out.println("Springdoc OpenAPI 文档地址：http://localhost:" + port + "/swagger-ui.html");
        System.out.println("Knife4j 文档地址：http://localhost:" + port + "/doc.html");
        System.out.println("----------------------------------------------------\n");
    }

}