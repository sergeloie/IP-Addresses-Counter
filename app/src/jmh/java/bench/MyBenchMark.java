package bench;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

import static ru.anseranser.DeepSeek.DeepSeek_textToNumericFormatV4;

public class MyBenchMark {

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void testDeep() {
        DeepSeek_textToNumericFormatV4("192.168.1.1");
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(MyBenchMark.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
