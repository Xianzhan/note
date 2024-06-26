package xianzhan.note.slf4j.logback;

//import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.UUID;

//@Slf4j
public class LogbackTest {

    private static final Logger log = LoggerFactory.getLogger(LogbackTest.class);

    @BeforeEach
    public void beforeEach() {
        MDC.put("logback-request-ip", "127.0.0.1");
        MDC.put("logback-request-id", UUID.randomUUID().toString());
    }

    @Test
    public void testInfo() {
        log.info("Logback info");
    }
}
