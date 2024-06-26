package xianzhan.note.spring.base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xianzhan.note.spring.base.service.IBaseService;
import xianzhan.note.spring.base.service.impl.BaseServiceImpl;

/**
 * @author xianzhan
 * @since 2023-05-23
 */
@Configuration
public class SpringBaseConfig {

    @Bean
    public IBaseService baseService() {
        return new BaseServiceImpl();
    }
}
