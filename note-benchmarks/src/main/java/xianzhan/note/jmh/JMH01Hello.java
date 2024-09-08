package xianzhan.note.jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * # JMH version: 1.35
 * # VM version: JDK 18, Java HotSpot(TM) 64-Bit Server VM, 18+36-2087
 * # VM invoker: C:\Users\42444\dev\bin\java\jdk-18\bin\java.exe
 * # VM options: -javaagent:C:\Users\42444\dev\bin\idea\ideaIU-2021.3.2\lib\idea_rt.jar=53861:C:\Users\42444\dev\bin\idea\ideaIU-2021.3.2\bin -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8
 * # Blackhole mode: compiler (auto-detected, use -Djmh.blackhole.autoDetect=false to disable)
 * # Warmup: 5 iterations, 10 s each
 * # Measurement: 5 iterations, 10 s each
 * # Timeout: 10 min per iteration
 * # Threads: 1 thread, will synchronize iterations
 * # Benchmark mode: Throughput, ops/time
 * # Benchmark: xianzhan.note.jmh.JMH01Hello.emptyMethod
 *
 * # Run progress: 0.00% complete, ETA 00:01:40
 * # Fork: 1 of 1
 * # Warmup Iteration   1: 2033161320.415 ops/s
 * # Warmup Iteration   2: 2005335513.577 ops/s
 * # Warmup Iteration   3: 3229306651.423 ops/s
 * # Warmup Iteration   4: 1922952283.492 ops/s
 * # Warmup Iteration   5: 1958227476.460 ops/s
 * Iteration   1: 1956525880.716 ops/s
 * Iteration   2: 1805242521.392 ops/s
 * Iteration   3: 1924252032.459 ops/s
 * Iteration   4: 1929802093.325 ops/s
 * Iteration   5: 3421323136.085 ops/s
 *
 *
 * Result "xianzhan.note.jmh.JMH01Hello.emptyMethod":
 *   2207429132.795 ±(99.9%) 2622618716.048 ops/s [Average]
 *   (min, avg, max) = (1805242521.392, 2207429132.795, 3421323136.085), stdev = 681085752.075
 *   CI (99.9%): [≈ 0, 4830047848.843] (assumes normal distribution)
 *
 *
 * # Run complete. Total time: 00:01:40
 *
 * REMEMBER: The numbers below are just data. To gain reusable insights, you need to follow up on
 * why the numbers are the way they are. Use profilers (see -prof, -lprof), design factorial
 * experiments, perform baseline and negative tests that provide experimental control, make sure
 * the benchmarking environment is safe on JVM/OS/HW level, ask for reviews from the domain experts.
 * Do not assume the numbers tell you what you want them to tell.
 *
 * NOTE: Current JVM experimentally supports Compiler Blackholes, and they are in use. Please exercise
 * extra caution when trusting the results, look into the generated code to check the benchmark still
 * works, and factor in a small probability of new VM bugs. Additionally, while comparisons between
 * different JVMs are already problematic, the performance difference caused by different Blackhole
 * modes can be very significant. Please make sure you use the consistent Blackhole mode for comparisons.
 *
 * Benchmark                Mode  Cnt           Score            Error  Units
 * JMH01Hello.emptyMethod  thrpt    5  2207429132.795 ± 2622618716.048  ops/s
 */
public class JMH01Hello {

    @Benchmark
    public void emptyMethod() {

    }

    public static void main() throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JMH01Hello.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();

        // # Warmup: 5 iterations, 10 s each                      | 预热：执行 5 次，每次 10 秒
        // # Measurement: 5 iterations, 10 s each                 | 测量：执行 5 次，每次 10 秒
        // # Timeout: 10 min per iteration
        // # Threads: 1 thread, will synchronize iterations
        // # Benchmark mode: Throughput, ops/time                 | 吞吐量的维度
        // # Benchmark: xianzhan.note.jmh.JMH01Hello.emptyMethod
    }
}
