package xianzhan.note.spring.base;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionCustomizer;
import xianzhan.note.spring.base.bean.impl.AImpl;

import java.util.function.Supplier;

/**
 * @author xianzhan
 * @since 2022-11-06
 */
public class BeanDefinitionTest {

    /**
     * BeanDefinition 创建流程
     *
     * @see org.springframework.context.annotation.AnnotationConfigApplicationContext#register(Class[])
     * @see org.springframework.context.annotation.AnnotatedBeanDefinitionReader#register(Class[])
     * @see org.springframework.context.annotation.AnnotatedBeanDefinitionReader#registerBean(Class)
     * @see org.springframework.context.annotation.AnnotatedBeanDefinitionReader#doRegisterBean(Class, String, Class[], Supplier, BeanDefinitionCustomizer[])
     */
    @Test
    public void testAnnotatedGenericAImpl() {
        BeanDefinition bd = new AnnotatedGenericBeanDefinition(AImpl.class);
        Assertions.assertNull(bd.getParentName());
        Assertions.assertEquals(AImpl.class.getName(), bd.getBeanClassName());
        Assertions.assertEquals("", bd.getScope());
        Assertions.assertFalse(bd.isLazyInit());
        Assertions.assertNull(bd.getDependsOn());

        Assertions.assertTrue(bd.isAutowireCandidate());
        Assertions.assertTrue(bd.isSingleton());

        Assertions.assertFalse(bd.isPrimary());
        Assertions.assertFalse(bd.isPrototype());
        Assertions.assertFalse(bd.isAbstract());

        Assertions.assertNull(bd.getFactoryBeanName());
        Assertions.assertNull(bd.getFactoryMethodName());

        Assertions.assertNull(bd.getInitMethodName());
        Assertions.assertNull(bd.getDestroyMethodName());
        Assertions.assertNull(bd.getOriginatingBeanDefinition());

        Assertions.assertEquals(BeanDefinition.ROLE_APPLICATION, bd.getRole());
    }
}
