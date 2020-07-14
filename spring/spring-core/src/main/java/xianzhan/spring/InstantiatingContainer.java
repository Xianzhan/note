package xianzhan.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import xianzhan.spring.bean.BBean;
import xianzhan.spring.config.ScanPackage;

/**
 * 实例化一个 spring 容器
 *
 * @author xianzhan
 * @since 2020-03-31
 */
public class InstantiatingContainer {

    public static void main(String[] args) {
        initAnnotation();
    }

    private static void initAnnotation() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(ScanPackage.class);
        var b = ac.getBean(BBean.class);
        System.out.println(b);
    }
}
