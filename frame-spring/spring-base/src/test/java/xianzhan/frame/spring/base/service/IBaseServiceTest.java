package xianzhan.frame.spring.base.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import xianzhan.frame.spring.base.config.AopConfig;
import xianzhan.frame.spring.base.config.SpringBaseConfig;

/**
 * @author xianzhan
 * @since 2023-05-23
 */
@SpringJUnitConfig(classes = {SpringBaseConfig.class, AopConfig.class})
public class IBaseServiceTest {

    @Autowired
    private IBaseService baseService;

    @Test
    void testPrintInfo() {
        Assertions.assertEquals("BaseServiceImpl#printInfo", baseService.printInfo());
    }
}
