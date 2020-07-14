package xianzhan.spring.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author xianzhan
 * @since 2020-07-13
 */
@Component
public class BBean {

    @Autowired
    private ABean a;

    public BBean() {
        System.out.println("BBean");
    }

    @Override
    public String toString() {
        return "BBean{" +
               "a=" + a +
               '}';
    }
}
