import java.util.HashMap;
import java.util.Map;

public class Leet1074_NumberOfSubmatricesThatSum2Target {
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int res = 0, h = matrix.length, w = matrix[0].length;

        for (int i = 0; i < h; ++i) {
            for (int j = 1; j < w; ++j) {
                matrix[i][j] += matrix[i][j - 1];
            }
        }

        for (int i = 0; i < w; ++i) {
            for (int j = i; j < w; ++j) {
                Map<Integer, Integer> cnt = new HashMap<>();
                cnt.put(0, 1);

                int cur = 0;
                for (int[] ints : matrix) {
                    cur += ints[j] - (i > 0 ? ints[i - 1] : 0);

                    if (cnt.containsKey(cur - target)) {
                        res += cnt.get(cur - target);
                    }

                    if (cnt.containsKey(cur)) {
                        cnt.compute(cur, (key, v) -> {
                            v += 1;
                            return v;
                        });
                    } else {
                        cnt.put(cur, 1);
                    }
                }
            }
        }

        return res;
    }
}
