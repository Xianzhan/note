package xianzhan.springcloud.eureka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author xianzhan
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApplication {

    @Value("http://${eureka.instance.hostname}:${server.port}")
    private String url;

    public static void main(String[] args) {
        var applicationContext = SpringApplication.run(EurekaServerApplication.class, args);
        var eurekaApplication = applicationContext.getBean(EurekaServerApplication.class);
        System.out.println("Dashboard: " + eurekaApplication.url);
    }
}
