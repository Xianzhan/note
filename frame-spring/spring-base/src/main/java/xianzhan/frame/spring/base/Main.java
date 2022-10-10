package xianzhan.frame.spring.base;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.stream.Stream;

/**
 * @author xianzhan
 * @since 2022-10-10
 */
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(".");
        String[] coreBeanDefinitions = context.getBeanDefinitionNames();
        Stream.of(coreBeanDefinitions)
                .forEach(System.out::println);
    }
}
