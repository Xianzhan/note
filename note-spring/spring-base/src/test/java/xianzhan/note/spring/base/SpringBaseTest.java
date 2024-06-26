package xianzhan.note.spring.base;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import xianzhan.note.spring.base.bean.IA;
import xianzhan.note.spring.base.bean.IB;

import java.util.ArrayList;
import java.util.List;

/**
 * Spring 功能测试
 *
 * @author xianzhan
 * @since 2022-10-20
 */
public class SpringBaseTest {

    static String basePackage = "xianzhan.note.spring.base.";

    protected AnnotationConfigApplicationContext context;

    public void getPackage(List<String> basePackages) {

    }

    @BeforeEach
    public void beforeAll() {
        var basePackages = new ArrayList<String>();
        basePackages.add(basePackage + "bean");
        getPackage(basePackages);

        context = new AnnotationConfigApplicationContext(basePackages.toArray(new String[0]));
    }

    /**
     * 依赖注入测试
     */
    @Test
    public void testDependInject() {
        IA a = context.getBean(IA.class);
        Assertions.assertNotNull(a);
    }

    /**
     * 循环依赖测试
     */
    @Test
    public void testCircular() {
        IA beanA = context.getBean(IA.class);
        IB beanB = context.getBean(IB.class);

        Assertions.assertEquals(beanA, beanB.getA());
        Assertions.assertEquals(beanB, beanA.getB());
    }
}
