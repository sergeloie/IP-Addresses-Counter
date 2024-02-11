package bench;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

import static ru.anseranser.IPConverter.textToNumericFormatV4;
import static ru.anseranser.IPUtils.ipToLong;

public class IPtoLongBench {

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public static void testStringCharByCharToIP() {
        textToNumericFormatV4("192.168.111.111");
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public static void testStringSplitToIP() {
        ipToLong("192.168.111.111");
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(IPtoLongBench.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
