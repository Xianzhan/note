package xianzhan.note.spring.base.single;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author xianzhan
 * @since 2023-02-15
 */
public class SingleTest {

    @Test
    public void test() {
        var packageName = SingleTest.class.getPackageName();
        ApplicationContext context = new AnnotationConfigApplicationContext(packageName);

        ISingleBean bean = context.getBean(ISingleBean.class);
        System.out.println(bean);
        Assertions.assertNotNull(bean);
    }
}
