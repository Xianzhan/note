package xianzhan.service.consumer.eureka.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author xianzhan
 * @since 2020-09-07
 */
@RequestMapping("/consumer")
@RestController
public class ConsumerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerController.class);

    @Value("${eureka.instance.hostname}")
    private String hostname;
    @Value("${server.port}")
    private String port;

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/info")
    public String info(@RequestParam(value = "name", defaultValue = "eureka-client-consumer-01") String name) {
        LOGGER.info("服务调用 ConsumerController::info");
        String providerInfo = restTemplate.getForObject("http://eureka-client-provider/provider/info", String.class);
        String ret = "<br>&nbsp&nbsp&nbsp&nbspName: " + name + "@" + hostname + ":" + port;
        return providerInfo + ret;
    }
}
