import org.openjdk.jmh.annotations.*;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 5, time = 5)
@Threads(4)
@Fork(1)
@State(value = Scope.Benchmark)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class Test {

    @Benchmark
    public static int[][] getRidOf(int n, int[][] roads) {
        int[][] transformer = new int[n + 1][2];

        for(int[] d : roads) {
            if(transformer[d[0]][0] != 0)
                transformer[d[0]][1] = d[1];
            else
                transformer[d[0]][0] = d[1];

            if(transformer[d[1]][0] != 0)
                transformer[d[1]][1] = d[0];
            else
                transformer[d[1]][0] = d[0];
        }

        for(int i = 1; i <= n; ++i) {
            if(transformer[i][1] == 0) {
                if(transformer[0][0] == 0)
                    transformer[0][0] = i;
                else
                    transformer[0][1] = i;
            }
        }

        int[] path = new int[n];
        path[0] = transformer[0][0];
        path[1] = transformer[path[0]][0];

        for(int i = 2; i <= n / 2; ++i) {
            if(transformer[path[i - 1]][0] == path[i - 2])
                path[i] = transformer[path[i - 1]][1];
            else
                path[i] = transformer[path[i - 1]][0];
        }

        int[][] res = new int[2][2];
        res[0][0] = path[0];
        res[0][1] = path[1];
        res[1][0] = path[0];
        res[1][1] = path[n / 2];

        return res;
    }

    public static void main(String[] args) {
        int[][] input = {{6,1},{3,4},{2,1},{5,3},{2,4}};

        int[][] res = Test.getRidOf(6, input);
        System.out.println(Arrays.toString(res[0]));
        System.out.println(Arrays.toString(res[1]));
    }
}
