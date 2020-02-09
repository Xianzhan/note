package xianzhan.slf4j.log4j2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        LOGGER.info(" hello world: {}", "Lee");
        try {
            throw new RuntimeException("runtime exception");
        } catch (RuntimeException e) {
            LOGGER.error("test ex", e);
        }
    }
}
