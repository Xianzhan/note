package xianzhan.frame.spring.base.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Arrays;

/**
 * AOP 配置
 *
 * @author xianzhan
 * @since 2023-05-23
 */
@Aspect
@Configuration
@EnableAspectJAutoProxy
public class AopConfig {

    @Pointcut("execution(* xianzhan.frame.spring.base.service.impl.BaseServiceImpl.printInfo())")
    public void pointCutAround() {

    }

    /**
     * 环绕通知
     *
     * @param p 切入点
     * @return 方法返回
     */
    @Around("pointCutAround()")
    public Object around(ProceedingJoinPoint p) {
        System.out.println("环绕通知：进入方法. 参数: " + Arrays.toString(p.getArgs()));
        Object ret;
        try {
            ret = p.proceed();
        } catch (Throwable e) {
            System.err.println("环绕通知：方法异常" + e);
            ret = null;
        }
        System.out.println("环绕通知：退出方法. 返回值：" + ret);
        return ret;
    }
}
