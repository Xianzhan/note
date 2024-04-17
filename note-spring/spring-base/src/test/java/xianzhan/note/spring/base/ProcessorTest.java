package xianzhan.note.spring.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import xianzhan.note.spring.base.bean.ID;
import xianzhan.note.spring.base.config.processor.DInstantiationAwareBeanPostProcessor;

import java.util.List;

/**
 * @author xianzhan
 * @since 2022-11-17
 */
public class ProcessorTest extends SpringBaseTest {

    private ID d;

    @BeforeEach
    public void before() {
        d = context.getBean(ID.class);
    }

    @Override
    public void getPackage(List<String> basePackages) {
        basePackages.add(basePackage + "config.processor");
    }

    /**
     * @see DInstantiationAwareBeanPostProcessor
     */
    @Test
    public void test() {
        /*
DInstantiationAwareBeanPostProcessor 实例化后置处理器构造方法
================================================================================
AImpl 实例化前方法 postProcessBeforeInstantiation
AImpl
AImpl 实例化后方法 postProcessAfterInstantiation
AImpl 实例化处理属性 postProcessProperties
================================================================================
BImpl 实例化前方法 postProcessBeforeInstantiation
BImpl
BImpl 实例化后方法 postProcessAfterInstantiation
BImpl 实例化处理属性 postProcessProperties
BImpl 实例化后初始前方法 postProcessBeforeInitialization
BImpl 实例化后初始后方法 postProcessAfterInitialization
AImpl 实例化后初始前方法 postProcessBeforeInitialization
AImpl 实例化后初始后方法 postProcessAfterInitialization
================================================================================
CImpl 实例化前方法 postProcessBeforeInstantiation
CImpl
CImpl 实例化后方法 postProcessAfterInstantiation
CImpl 实例化处理属性 postProcessProperties
CImpl 实例化后初始前方法 postProcessBeforeInitialization
CImpl 实例化后初始后方法 postProcessAfterInitialization
================================================================================
DImpl 实例化前方法 postProcessBeforeInstantiation
DImpl
DImpl 实例化后方法 postProcessAfterInstantiation
DImpl 实例化处理属性 postProcessProperties
DImpl 实例化后初始前方法 postProcessBeforeInitialization
DImpl 实例化后初始后方法 postProcessAfterInitialization
         */
    }
}
