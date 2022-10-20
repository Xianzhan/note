package xianzhan.frame.spring.base.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xianzhan.frame.spring.base.bean.A;

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
