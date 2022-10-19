package xianzhan.frame.spring.base.circular;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author xianzhan
 * @since 2022-10-10
 */
@Component
public class B {

    @Autowired
    private A a;

    public B() {
        System.out.println("B constructor");
    }

    public A getA() {
        return a;
    }
}
