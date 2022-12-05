package xianzhan.frame.spring.base;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * @author xianzhan
 * @implNote <a href="https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#expressions">SpEL</a>
 * @since 2022-12-04
 */
public class ExpressionLanguageTest {

    @Test
    public void testString() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("'Hello World'");
        String message = (String) exp.getValue();
        Assertions.assertEquals("Hello World", message);
    }

    @Test
    public void testStringConcat() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("'Hello World'.concat('!')");
        String message = (String) exp.getValue();
        Assertions.assertEquals("Hello World!", message);
    }

    @Test
    public void testStringBytes() {
        ExpressionParser parser = new SpelExpressionParser();

        // invokes 'getBytes()'
        Expression exp = parser.parseExpression("'Hello World'.bytes");
        byte[] bytes = (byte[]) exp.getValue();
        Assertions.assertArrayEquals("Hello World".getBytes(), bytes);
    }

    @Test
    public void testStringBytesLength() {
        ExpressionParser parser = new SpelExpressionParser();

        // invokes 'getBytes()'
        Expression exp = parser.parseExpression("'Hello World'.bytes.length");
        Integer length = (Integer) exp.getValue();
        Assertions.assertEquals("Hello World".getBytes().length, length);
    }

    @Test
    public void testStringClass() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("new String('hello world').toUpperCase()");
        String message = exp.getValue(String.class);
        Assertions.assertEquals("HELLO WORLD", message);
    }
}
