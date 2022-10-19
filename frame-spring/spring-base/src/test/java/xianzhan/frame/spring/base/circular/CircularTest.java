package xianzhan.frame.spring.base.circular;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author xianzhan
 * @since 2022-10-10
 */
public class CircularTest {

    @Test
    public void testCircular() {
        String basePackages = "xianzhan.frame.spring.base.circular";
        ApplicationContext context = new AnnotationConfigApplicationContext(basePackages);

        A beanA = context.getBean(A.class);
        B beanB = context.getBean(B.class);

        Assertions.assertEquals(beanA, beanB.getA());
        Assertions.assertEquals(beanB, beanA.getB());
    }
}
