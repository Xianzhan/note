package xianzhan.service.registry.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author xianzhan
 * @since 2020-09-05
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApplication {

    /**
     * 选择下面参数其一启动, 高可用注册中心
     *
     * @param args --spring.profiles.active=eureka-server-01
     *             --spring.profiles.active=eureka-server-02
     * @implNote application.yml
     */
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}
