package xianzhan.frame.concurrent.jdk;

/**
 * <a href="https://www.youtube.com/watch?v=fjvGzBFmyhM">...</a>
 *
 * @author xianzhan
 * @since 2023-06-25
 */
public class ScopedValueTest {

    private static final ScopedValue<String> KEY = ScopedValue.newInstance();

    // --enable-preview --add-modules jdk.incubator.concurrent

    public static void main(String[] args) throws Exception {
        String result = ScopedValue.where(KEY, "ABC")
                .call(() -> {
                    // true
                    System.out.println(KEY.isBound());
                    if (!KEY.isBound()) {
                        return "KEY is not bound";
                    }

                    // 根据 isBound() 方法避免 NoSuchElementException
                    if (KEY.get().equals("ABC")) {
                        return "Hello ABC";
                    } else {
                        return "Not ABC";
                    }
                });

        // Hello ABC
        System.out.println(result);

        // false
        System.out.println(KEY.isBound());

        Runnable task = () -> System.out.println("KEY -> " + (KEY.isBound() ? KEY.get() : "Unbound"));
        // KEY -> Unbound
        task.run();
        // KEY -> A
        ScopedValue.where(KEY, "A").run(task);
        // KEY -> B
        ScopedValue.where(KEY, "B").run(task);
        // KEY -> Unbound
        task.run();
    }
}
