package xianzhan.note.spring.base.bean.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xianzhan.note.spring.base.bean.IA;
import xianzhan.note.spring.base.bean.IC;

/**
 * @author xianzhan
 * @since 2022-11-06
 */
@Service
public class CImpl implements IC {

    @Autowired
    private IA a;

    public CImpl() {
        System.out.println("CImpl");
    }

    @Override
    public void serviceC() {
        System.out.println("serviceC");
        a.serviceA();
    }

    @Override
    public String around(String param) {
        System.out.printf("C#around: param: %s, return: %s%n", param, "C#around");
        return "C#around";
    }

    @Override
    public void before() {
        System.out.println("C#before");
    }

    @Override
    public void after() {
        System.out.println("C#after");
    }

    @Override
    public String afterReturning() {
        System.out.printf("C#after: return: %s%n", "C#afterReturning");
        return "C#afterReturning";
    }

    @Override
    public void afterThrowing() throws RuntimeException {
        System.out.println("C#afterThrowing");
        throw new RuntimeException("C#afterThrowing");
    }
}
