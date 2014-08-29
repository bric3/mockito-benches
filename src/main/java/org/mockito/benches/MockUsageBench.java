package org.mockito.benches;

import org.mockito.Mockito;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Map;

import static org.mockito.BDDMockito.given;

@State(Scope.Benchmark)
public class MockUsageBench {

    private Map map;
    private Object key = new Object();
    private Object key2 = new Object();

    @Setup
    public void create_mock() {
        map = Mockito.mock(Map.class);
        given(map.get(new Object())).willReturn(new Object());
        given(map.get(key)).willReturn(null);
    }

    @Benchmark
    public void measure_baseline_mock(Blackhole blackhole) {
        blackhole.consume(map.get(key2));
    }

    @Benchmark
    public void measure_map_mock(Blackhole blackhole) {
        blackhole.consume(map.get(key));
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    public void measure_baseline_method_call(Blackhole blackhole) {
        blackhole.consume(map.size());
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(".*" + MockUsageBench.class.getSimpleName() + ".*")
                .warmupIterations(6)
                .measurementIterations(6)
                .threads(1)
                .forks(1)
                .build();

        new Runner(opt).run();
    }


}
