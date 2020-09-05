package xianzhan.service.provider.eureka.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xianzhan
 * @since 2020-09-05
 */
@RequestMapping("/provider")
@RestController
public class ProviderController {

    @Value("${eureka.instance.hostname}")
    private String hostname;

    @Value("${server.port}")
    private String port;

    @GetMapping("/info")
    public String info(@RequestParam(value = "name", defaultValue = "eureka-client-01") String name) {
        return "Name: " + name + "@" + hostname + ":" + port;
    }
}
