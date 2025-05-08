package org.feather.bz;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**

 * @author: feather
 */
@Slf4j
@SpringBootApplication(scanBasePackages = "org.feather.bz")
@MapperScan("org.feather.bz.mapper")
@EnableMethodCache(basePackages = "org.feather.bz")
@EnableCreateCacheAnnotation
public class UserApplication {



    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext application = SpringApplication.run(UserApplication.class, args);
        Environment env = application.getEnvironment();
        log.info(
                "\n----------------------------------------------------------\n\t"
                        + "Application '{}' is running! Access URLs:\n\t" + "Local: \t\thttp://localhost:{}\n\t"
                        + "External: \thttp://{}:{}\n\t" + "Doc: \thttp://{}:{}/doc.html\n"
                        + "----------------------------------------------------------",
                env.getProperty("spring.application.name"), env.getProperty("server.port"),
                InetAddress.getLocalHost().getHostAddress(), env.getProperty("server.port"),
                InetAddress.getLocalHost().getHostAddress(), env.getProperty("server.port"));
    }


}