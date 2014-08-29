
package org.mockito.benches;

import org.mockito.Mockito;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.List;

//@State(Scope.Benchmark)
public class MockitoCreationBench {

//    private MockUtil mockUtil = new MockUtil();

    @Benchmark
    public void mock_some_bootstrap_interface() {
        Mockito.mock(List.class);
    }



//    @Benchmark
//    public void create_lot_of_mocks_in_same_classloader() {
//        CreationSettings<List> settings = new CreationSettings<List>();
//        settings.setTypeToMock(List.class);
//        settings.setMockName(new MockNameImpl("toto"));
//
//        mockUtil.createMock(settings);
//    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(".*" + MockitoCreationBench.class.getSimpleName() + ".*")
                .warmupIterations(5)
                .measurementIterations(5)
                .threads(4)
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
