import java.util.Arrays;

public class Leet279_PerfectSquares {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 0; i <= n; ++i) {
            for (int j = 1; i + j * j <= n; ++j) {
                if (dp[i] + 1 < dp[i + j * j]) {
                    dp[i + j * j] = dp[i] + 1;
                }
            }
        }

        return dp[n];
    }
}
