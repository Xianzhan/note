package xianzhan.frame.spring.base.bean;

/**
 * AOP 测试服务
 *
 * @author xianzhan
 * @since 2022-11-06
 */
public interface IC {

    void serviceC();

    String around(String param);

    void before();

    void after();

    String afterReturning();

    void afterThrowing() throws RuntimeException;
}
