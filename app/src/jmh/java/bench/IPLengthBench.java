package bench;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import ru.anseranser.DeepSeek;
import ru.anseranser.Openjdk;

import java.util.concurrent.TimeUnit;

import static ru.anseranser.IPUtils.ipToLong;

public class IPLengthBench {

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void testOpen4Digit() {
        Openjdk.Openjdk_textToNumericFormatV4("1.9.8.5");
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void testOpen8Digit() {
        Openjdk.Openjdk_textToNumericFormatV4("19.16.11.11");
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void testOpen12Digit() {
        Openjdk.Openjdk_textToNumericFormatV4("192.168.111.111");
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(IPLengthBench.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
