package xianzhan.frame.spring.base;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import xianzhan.frame.spring.base.bean.impl.AImpl;

/**
 * @author xianzhan
 * @since 2022-11-06
 */
public class BeanDefinitionTest {

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
