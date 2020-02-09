package xianzhan.slf4j.log4j12;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        log.debug("hello log4j12");

        int result = sum(2, 2);
        log.error("2 + 2 is " + result);
    }

    private static int sum(int a, int b) {
        return a + b;
    }
}
