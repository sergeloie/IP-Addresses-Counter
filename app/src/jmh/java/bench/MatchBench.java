package bench;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

import static bench.RegexpTest.isIPv4Address;
import static bench.RegexpTest.isIPv4AddressOuter;

@State(Scope.Thread)
public class MatchBench {

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public static void isIPv4AddressInnerRegex() {
        isIPv4Address("192.168.125.147");
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public static void isIPv4AddressOuterRegex() {
        isIPv4AddressOuter("192.168.125.147");
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(MatchBench.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
