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

public class IPtoLongBench {

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void testOpen() {
        Openjdk.Openjdk_textToNumericFormatV4("192.168.111.111");
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void testSplit() {
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
