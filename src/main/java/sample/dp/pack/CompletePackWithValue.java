package sample.dp.pack;

import java.util.ArrayList;
import java.util.List;

public class CompletePackWithValue {
    /**
     * 背包大小为 target
     * created in 16:53 2021/5/31
     */
    public int[] backPack(int[] weights, int[] values, int target) {
        int num = weights.length;

        int[] dp = new int[target + 1];

        for (int i = 0; i < num; ++i) {
            int weight = weights[i], value = values[i];
            for (int j = weight; j <= target; ++j) {
                if (dp[j - weight] + value > dp[j]) {
                    dp[j] = dp[j - weight] + value;
                }
            }
        }

        List<Integer> res = new ArrayList<>();
        int idx = target;

        while (idx > 0) {
            int tempRes = -1;
            for (int i = num - 1; i > 0; --i) {
                if (dp[idx - weights[i]] + values[i] == dp[idx]) {
                    tempRes = i;
                    idx -= weights[tempRes];
                    break;
                }
            }

            if (tempRes != -1) {
                res.add(tempRes);
            }
        }

        int[] ans = new int[res.size()];
        idx = 0;
        for (Integer i : res) {
            ans[idx++] = i;
        }

        return ans;
    }
}
