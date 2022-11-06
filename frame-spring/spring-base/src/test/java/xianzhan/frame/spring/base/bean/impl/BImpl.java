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
public class BImpl implements IB {

    @Autowired
    private IA a;

    public BImpl() {
        System.out.println("BImpl");
    }

    @Override
    public IA getA() {
        return a;
    }

    @Override
    public void serviceB() {
        System.out.println("serviceB");
    }
}
