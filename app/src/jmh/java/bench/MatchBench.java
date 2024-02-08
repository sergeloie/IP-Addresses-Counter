package bench;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

import static ru.anseranser.IPUtilsTest.isIPv4Address;
import static ru.anseranser.IPUtilsTest.isIPv4AddressOuter;

@State(Scope.Thread)
public class MatchBench {

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void panInner(MatchBench state, Blackhole blackhole) {
        blackhole.consume(isIPv4Address("192.168.125.147"));

    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void panOuter(MatchBench state, Blackhole blackhole) {
        blackhole.consume(isIPv4AddressOuter("192.168.125.147"));
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(MatchBench.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
