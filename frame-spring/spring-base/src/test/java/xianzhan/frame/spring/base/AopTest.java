package xianzhan.frame.spring.base;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import xianzhan.frame.spring.base.bean.IC;

import java.util.List;

/**
 * @author xianzhan
 * @since 2022-11-06
 */
public class AopTest extends SpringBaseTest {

    private IC c;

    @BeforeEach
    public void before() {
        c = context.getBean(IC.class);
    }

    @Override
    public void getPackage(List<String> basePackages) {
        basePackages.add(basePackage + "config.aop");
    }

    @Test
    public void testAround() {
        String around = c.around("param");
        Assertions.assertEquals("C#around", around);
    }

    @Test
    public void testBefore() {
        c.before();
    }

    @Test
    public void testAfter() {
        c.after();
    }

    @Test
    public void testAfterReturning() {
        Assertions.assertEquals("C#afterReturning", c.afterReturning());
    }

    @Test()
    public void testAfterThrowing() {
        Assertions.assertThrowsExactly(RuntimeException.class, () -> c.afterThrowing());
    }
}
