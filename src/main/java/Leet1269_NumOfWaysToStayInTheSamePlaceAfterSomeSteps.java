public class Leet1269_NumOfWaysToStayInTheSamePlaceAfterSomeSteps {
    final static int MOD = 1_000_000_007;

    /**
     * DP:
     *  dp[i][j] 代表第 i 步在 j 格的走法数
     * created in 19:11 2021/5/13
     */
    public int numWays(int steps, int arrLen) {
        if (arrLen == 0)
            return 1;

        int numColumn = Math.min(steps + 1, arrLen);
        int[][] dp = new int[steps + 1][numColumn];

        dp[0][0] = 1;

        for (int step = 1; step <= steps; ++step) {
            if (arrLen > 1)
                dp[step][0] = (dp[step - 1][0] + dp[step - 1][1]) % MOD;

            for (int pos = 1; pos < numColumn; ++pos) {
                if (pos > step) {
                    break;
                }

                dp[step][pos] = (dp[step - 1][pos] + dp[step - 1][pos - 1]) % MOD;

                if (pos + 1 < numColumn) {
                    dp[step][pos] = (dp[step][pos] + dp[step - 1][pos + 1]) % MOD;
                }
            }
        }

        return dp[steps][0];
    }
}
