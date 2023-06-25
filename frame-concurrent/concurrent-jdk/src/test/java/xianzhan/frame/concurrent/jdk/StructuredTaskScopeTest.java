package xianzhan.frame.concurrent.jdk;

import jdk.incubator.concurrent.StructuredTaskScope;

import java.time.LocalDateTime;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author xianzhan
 * @since 2023-06-25
 */
public class StructuredTaskScopeTest {

    // --enable-preview --add-modules jdk.incubator.concurrent

    public static void main(String[] args) throws InterruptedException {
        System.out.println(LocalDateTime.now());
        try (var scope = new StructuredTaskScope<String>()) {
            Future<String> forkA = scope.fork(StructuredTaskScopeTest::taskA);
            Future<String> forkB = scope.fork(StructuredTaskScopeTest::taskB);

            scope.join();

            System.out.println(forkA.resultNow());
            System.out.println(forkB.resultNow());
        }
        System.out.println(LocalDateTime.now());
    }

    public static String taskA() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        return "sleep 2s";
    }

    public static String taskB() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        return "sleep 3s";
    }
}
