package xianzhan.note.jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * # JMH version: 1.35
 * # VM version: JDK 18, Java HotSpot(TM) 64-Bit Server VM, 18+36-2087
 * # VM invoker: C:\Users\42444\dev\bin\java\jdk-18\bin\java.exe
 * # VM options: -javaagent:C:\Users\42444\dev\bin\idea\ideaIU-2021.3.2\lib\idea_rt.jar=54393:C:\Users\42444\dev\bin\idea\ideaIU-2021.3.2\bin -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8
 * # Blackhole mode: compiler (auto-detected, use -Djmh.blackhole.autoDetect=false to disable)
 * # Warmup: 1 iterations, 1 s each
 * # Measurement: 1 iterations, 1 s each
 * # Timeout: 10 min per iteration
 * # Threads: 1 thread, will synchronize iterations
 * # Benchmark mode: Throughput, ops/time
 * # Benchmark: xianzhan.note.jmh.JMH02WarmupMeasurement.emptyMethod
 *
 * # Run progress: 0.00% complete, ETA 00:00:02
 * # Fork: 1 of 1
 * # Warmup Iteration   1: 2002672311.235 ops/s
 * Iteration   1: 2017797682.539 ops/s
 *
 *
 * Result "xianzhan.note.jmh.JMH02WarmupMeasurement.emptyMethod":
 *   2017797682.539 ops/s
 *
 *
 * # Run complete. Total time: 00:00:02
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
 * Benchmark                            Mode  Cnt           Score   Error  Units
 * JMH02WarmupMeasurement.emptyMethod  thrpt       2017797682.539          ops/s
 *
 * Process finished with exit code 0
 */

// 预热一次，每次一秒
@Warmup(iterations = 1, time = 1, timeUnit = TimeUnit.SECONDS)
// 测量一次，每次一秒
@Measurement(iterations = 1, time = 1, timeUnit = TimeUnit.SECONDS)
public class JMH02WarmupMeasurement {

    @Benchmark
    public void emptyMethod() {

    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JMH02WarmupMeasurement.class.getSimpleName())
                .forks(1)
                .build();

        // # Warmup: 1 iterations, 1 s each                                   | 预热：执行 1 次，每次 1 秒
        // # Measurement: 1 iterations, 1 s each                              | 测量：执行 1 次，每次 1 秒
        // # Timeout: 10 min per iteration
        // # Threads: 1 thread, will synchronize iterations
        // # Benchmark mode: Throughput, ops/time                             | 吞吐量的维度
        // # Benchmark: xianzhan.note.jmh.JMH02WarmupMeasurement.emptyMethod
        new Runner(opt).run();
    }
}
