package xianzhan.service.provider.eureka.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(ProviderController.class);

    @Value("${eureka.instance.hostname}")
    private String hostname;

    @Value("${server.port}")
    private String port;

    @GetMapping("/info")
    public String info(@RequestParam(value = "name", defaultValue = "eureka-client-provider-01") String name) {
        LOGGER.info("服务调用 ProviderController::info");
        return "Name: " + name + "@" + hostname + ":" + port;
    }
}
