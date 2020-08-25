package xianzhan.springcloud.eureka.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xianzhan
 */
@RequestMapping("/business")
@RestController
public class BusinessController {

    @Value("${eureka.instance.hostname}")
    private String hostname;

    @Value("${server.port}")
    private String port;

    @GetMapping("/info")
    public String info(@RequestParam(value = "name", defaultValue = "eureka-client-01") String name) {
        return "Name: " + name + "@" + hostname + ":" + port;
    }
}
