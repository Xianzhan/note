package xianzhan.frame.spring.base;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import xianzhan.frame.spring.base.bean.A;
import xianzhan.frame.spring.base.bean.B;

/**
 * Spring 功能测试
 *
 * @author xianzhan
 * @since 2022-10-20
 */
public class SpringBaseTest {

    private static final String BEAN = "xianzhan.frame.spring.base.bean";

    /**
     * 依赖注入测试
     */
    @Test
    public void testDependInject() {
        var context = new AnnotationConfigApplicationContext(BEAN);
        A a = context.getBean(A.class);
        Assertions.assertNotNull(a);
    }

    /**
     * 循环依赖测试
     */
    @Test
    public void testCircular() {
        ApplicationContext context = new AnnotationConfigApplicationContext(BEAN);

        A beanA = context.getBean(A.class);
        B beanB = context.getBean(B.class);

        Assertions.assertEquals(beanA, beanB.getA());
        Assertions.assertEquals(beanB, beanA.getB());
    }
}
