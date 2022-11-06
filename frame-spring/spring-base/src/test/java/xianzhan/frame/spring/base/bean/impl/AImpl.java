package xianzhan.frame.spring.base.bean.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xianzhan.frame.spring.base.bean.IA;
import xianzhan.frame.spring.base.bean.IB;

/**
 * @author xianzhan
 * @since 2022-11-06
 */
@Service
public class AImpl implements IA {

    @Autowired
    private IB b;

    public AImpl() {
        System.out.println("AImpl");
    }

    @Override
    public IB getB() {
        return b;
    }

    @Override
    public void serviceA() {
        System.out.println("serviceA");
    }
}
