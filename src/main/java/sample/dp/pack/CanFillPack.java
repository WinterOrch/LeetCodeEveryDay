package sample.dp.pack;

import java.util.Arrays;

public class CanFillPack {
    public boolean canFillPack(int[] weights, int[] nums, int cap) {
        int n = weights.length;

        int[][] dp = new int[n + 1][cap + 1];
        for (int i = 0; i <= n; ++i) {
            Arrays.fill(dp[i], -1);
        }
        dp[0][0] = 0;

        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j <= cap; ++j) {
                if (dp[i - 1][j] >= 0)
                    dp[i][j] = nums[i];
                else
                    dp[i][j] = -1;
            }
            for (int j = 0; j <= cap - weights[i]; ++j) {
                if (dp[i][j] > 0 && dp[i][j + weights[i]] < dp[i][j] - 1) {
                    dp[i][j + weights[i]] = dp[i][j] - 1;
                }
            }
        }

        return dp[n][cap] != -1;
    }
}
