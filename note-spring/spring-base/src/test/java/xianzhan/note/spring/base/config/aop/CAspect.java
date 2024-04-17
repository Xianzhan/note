package xianzhan.note.spring.base.config.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author xianzhan
 * @since 2022-11-06
 */
@Aspect
@Component
@EnableAspectJAutoProxy
public class CAspect {

    @Pointcut("execution(* xianzhan.note.spring.base.bean.IC.around(..))")
    public void pointCutAround() {

    }

    @Pointcut("execution(* xianzhan.note.spring.base.bean.IC.before(..))")
    public void pointCutBefore() {

    }

    @Pointcut("execution(* xianzhan.note.spring.base.bean.IC.after(..))")
    public void pointCutAfter() {

    }

    @Pointcut("execution(* xianzhan.note.spring.base.bean.IC.afterReturning(..))")
    public void pointCutAfterReturning() {

    }

    @Pointcut("execution(* xianzhan.note.spring.base.bean.IC.afterThrowing(..))")
    public void pointCutAfterThrowing() {

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
            System.err.println("环绕通知：方法异常");
            throw new RuntimeException(e);
        }
        System.out.println("环绕通知：退出方法. 返回值：" + ret);
        return ret;
    }

    /**
     * 前置通知
     */
    @Before("pointCutBefore()")
    public void before() {
        System.out.println("前置通知");
    }

    /**
     * 后置通知
     */
    @After("pointCutAfter()")
    public void after() {
        System.out.println("后置通知");
    }

    /**
     * 返回通知
     *
     * @param ret 返回值
     */
    @AfterReturning(pointcut = "pointCutAfterReturning()", returning = "ret")
    public void pointCutAfterReturning(Object ret) {
        System.out.println("后置通知：返回 " + ret);
    }

    /**
     * 异常通知
     *
     * @param throwable 异常
     */
    @AfterThrowing(pointcut = "pointCutAfterThrowing()", throwing = "throwable")
    public void pointCutAfterThrowing(Throwable throwable) {
        System.out.println("异常通知：" + throwable.getMessage());
    }
}
