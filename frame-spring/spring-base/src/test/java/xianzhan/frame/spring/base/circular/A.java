package xianzhan.frame.spring.base.circular;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author xianzhan
 * @since 2022-10-10
 */
@Component
public class A {

    @Autowired
    private B b;

    public A() {
        System.out.println("A constructor");
    }
}
