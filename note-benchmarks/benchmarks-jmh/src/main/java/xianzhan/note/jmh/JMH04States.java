package xianzhan.note.jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@Warmup(iterations = 1, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 1, time = 1, timeUnit = TimeUnit.SECONDS)
public class JMH04States {

    /**
     * 这个静态内部类会在 Benchmark 的各个线程执行前初始化，作为入参传入
     * 作用域是整个 Benchmark
     */
    @State(Scope.Benchmark)
    public static class BenchmarkState {
        volatile double x = Math.PI;
    }

    @Benchmark
    public void measureShared(BenchmarkState state) {
        // 多线程共享
        state.x++;
    }

    /**
     * 这个静态内部类会在 Benchmark 的各个线程执行前初始化，作为入参传入
     * 作用域是当前线程 Thread
     */
    @State(Scope.Thread)
    public static class ThreadState {
        volatile double x = Math.PI;
    }

    @Benchmark
    public void measureUnshared(ThreadState state) {
        // 单线程独享
        state.x++;
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JMH04States.class.getSimpleName())
                .threads(4)
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
