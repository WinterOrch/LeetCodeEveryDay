import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Leet1787_MakeTheXorOfAllSegmentsEqualToZero {
    // x 的范围为 [0, 2^10)
    static final int MAX_X = 1 << 10;
    // 极大值，为了防止整数溢出选择 INT_MAX / 2
    static final int INF = Integer.MAX_VALUE / 2;

    public int minChanges(int[] nums, int k) {
        int len = nums.length;

        if (k == 1) {
            int ans = 0;
            for (int num : nums) {
                if (num != 0)
                    ++ans;
            }
            return ans;

        } else if (k == len) {
            int tmp = 0;
            for (int num : nums) {
                tmp ^= num;
            }
            return (tmp == 0) ? 0 : 1;

        }

        int[] f = new int[MAX_X];
        Arrays.fill(f, INF);
        f[0] = 0;

        for (int i = 0; i < k; ++i) {
            Map<Integer, Integer> cnt = new HashMap<>();
            int size = 0;
            for (int j = i; j < len; j += k) {
                if (cnt.containsKey(nums[j])) {
                    cnt.compute(nums[j], (key, v) -> {
                        v += 1;
                        return v;
                    });
                } else {
                    cnt.put(nums[j], 1);
                }
                ++size;
            }

            int t2min = Integer.MAX_VALUE;
            for (int dd : f) {
                if (dd < t2min)
                    t2min = dd;
            }

            int[] g = new int[MAX_X];
            Arrays.fill(g, t2min);
            for (int mask = 0; mask < MAX_X; ++mask) {
                for (Map.Entry<Integer, Integer> entry : cnt.entrySet()) {
                    int tmp = f[mask ^ entry.getKey()] - entry.getValue();

                    if (tmp < g[mask]) {
                        g[mask] = tmp;
                    }
                }
            }

            for (int j = 0; j < MAX_X; ++j) {
                g[j] += size;
            }
            f = g;
        }

        return f[0];
    }

    public static void main(String[] args) {
        int[] input = {3, 4, 5, 2, 1, 7, 3, 4, 7};
        Leet1787_MakeTheXorOfAllSegmentsEqualToZero ins = new Leet1787_MakeTheXorOfAllSegmentsEqualToZero();

        System.out.println(ins.minChanges(input, 3));
    }
}
