package xianzhan.frame.spring.base.config.processor;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * D 实例化后置处理器
 * <p>
 * 执行顺序：
 * 1. postProcessAfterInstantiation
 * 2. postProcessProperties
 * 3. postProcessBeforeInitialization
 * 4. postProcessAfterInitialization
 * 5. postProcessBeforeInstantiation
 *
 * @author xianzhan
 * -- 创建后置处理器
 * @see AbstractApplicationContext#refresh() Spring 刷新上下文
 * @see AbstractApplicationContext#registerBeanPostProcessors(ConfigurableListableBeanFactory) 注册后置处理器
 * @see org.springframework.context.support.PostProcessorRegistrationDelegate#registerBeanPostProcessors(ConfigurableListableBeanFactory, AbstractApplicationContext)
 * @see org.springframework.context.support.PostProcessorRegistrationDelegate#sortPostProcessors(List, ConfigurableListableBeanFactory) PostProcessor 排序
 * @see org.springframework.beans.factory.support.AbstractBeanFactory#addBeanPostProcessor(BeanPostProcessor)
 * -- 创建普通 bean 方法
 * @see org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#createBean(String, RootBeanDefinition, Object[])
 * -- BeanPostProcessor 执行实例化前后
 * @see org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#resolveBeforeInstantiation(String, RootBeanDefinition) BeanPostProcessor
 * -- 若 BeanPostProcessor 未创建 bean 则由 doCreateBean 方法创建
 * @see org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#doCreateBean(String, RootBeanDefinition, Object[])
 * -- BeanPostProcessor 处理依赖
 * @see org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#populateBean(String, RootBeanDefinition, BeanWrapper) BeanPostProcessor 依赖注入
 * -- BeanPostProcessor 执行初始化前后
 * @see org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#initializeBean(String, Object, RootBeanDefinition) BeanPostProcessor
 * @since 2022-11-17
 */
@Component
public class DInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

    public DInstantiationAwareBeanPostProcessor() {
        System.out.println(this.getClass().getSimpleName() + " 实例化后置处理器构造方法");
    }

    /**
     * 在实例化目标 bean 之前应用此 BeanPostProcessor。
     * 返回的 bean 对象可以是用来代替目标 bean 的代理，有效地抑制了目标 bean 的默认实例化。
     *
     * @param beanClass the class of the bean to be instantiated
     * @param beanName  the name of the bean
     * @return bean
     * @throws BeansException BeansException
     * @see org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#resolveBeforeInstantiation(String, RootBeanDefinition)
     */
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        System.out.println("================================================================================");
        System.out.println(beanName + " 实例化前方法 postProcessBeforeInstantiation");
        return InstantiationAwareBeanPostProcessor.super.postProcessBeforeInstantiation(beanClass, beanName);
    }

    /**
     * 在 bean 实例化之后，通过构造函数或工厂方法执行操作，
     * 但在 Spring 属性填充(从显式属性或自动装配)发生之前执行操作。
     *
     * @param bean     the bean instance created, with properties not having been set yet
     * @param beanName the name of the bean
     * @return bean
     * @throws BeansException BeansException
     * @see org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#resolveBeforeInstantiation(String, RootBeanDefinition)
     */
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        System.out.println(beanName + " 实例化后方法 postProcessAfterInstantiation");
        return InstantiationAwareBeanPostProcessor.super.postProcessAfterInstantiation(bean, beanName);
    }

    /**
     * 在工厂将给定的属性值应用到给定的 bean 之前，对它们进行后处理。
     *
     * @param pvs      the property values that the factory is about to apply (never {@code null})
     * @param bean     the bean instance created, but whose properties have not yet been set
     * @param beanName the name of the bean
     * @return bean
     * @throws BeansException BeansException
     * @see org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#populateBean(String, RootBeanDefinition, BeanWrapper)
     */
    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        System.out.println(beanName + " 实例化处理属性 postProcessProperties");
        return InstantiationAwareBeanPostProcessor.super.postProcessProperties(pvs, bean, beanName);
    }

    /**
     * 在任何 bean 初始化回调(如 InitializingBean 的 afterPropertiesSet 或自定义初始化方法)之前，
     * 将此 BeanPostProcessor 应用到给定的新 bean 实例。
     * bean 将已经填充了属性值。
     * 返回的 bean 实例可以是原始 bean 实例的包装器。
     *
     * @param bean     the new bean instance
     * @param beanName the name of the bean
     * @return bean
     * @throws BeansException BeansException
     * @see org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#initializeBean(String, Object, RootBeanDefinition)
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(beanName + " 实例化后初始前方法 postProcessBeforeInitialization");
        return InstantiationAwareBeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    /**
     * 在任何 bean 初始化回调(如 InitializingBean 的 afterPropertiesSet 或自定义初始化方法)之后，
     * 将此 BeanPostProcessor 应用到给定的新 bean 实例。
     * bean 将已经填充了属性值。
     * 返回的 bean 实例可以是原始 bean 实例的包装器。
     *
     * @param bean     the new bean instance
     * @param beanName the name of the bean
     * @return bean
     * @throws BeansException BeansException
     * @see org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#initializeBean(String, Object, RootBeanDefinition)
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(beanName + " 实例化后初始后方法 postProcessAfterInitialization");
        return InstantiationAwareBeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
