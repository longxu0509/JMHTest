import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @author xulong
 * @version 1.0
 * @email longxu@mail.ustc.edu.cn
 */

@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 1, time = 1)
@Measurement(iterations = 3)
@Threads(4)
@Fork(1)
@State(value = Scope.Benchmark)
@OutputTimeUnit(TimeUnit.SECONDS)

public class keyPairGenTest {
    private SM2 sm2;

    @Param(value = {"MontgomeryMuti", "doubleAndAddInc", "doubleAndAddDec"})
    private String multiply;

    @Setup
    public void setUp() throws Exception {
        sm2 = SM2.Instance();
    }

    @Benchmark
    public void testMontgomery() throws Exception {
        sm2.generateKeyPair(multiply);
    }

    public static void main(String[] args) throws Exception {
        Options opt = new OptionsBuilder()
                .include(keyPairGenTest.class.getSimpleName())
                .result("result.json")
                .resultFormat(ResultFormatType.JSON).build();
        new Runner(opt).run();
    }
}
