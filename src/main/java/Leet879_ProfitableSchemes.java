public class Leet879_ProfitableSchemes {
    private static final int MOD = 1_000_000_007;

    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        int[][] dp = new int[n + 1][minProfit + 1];
        dp[0][0] = 1;

        int numWorks = group.length;
        for (int k = 1; k <= numWorks; ++k) {
            int prof = profit[k - 1], gr = group[k - 1];
            for (int i = n; i >= gr; --i) {
                for (int j = minProfit; j >= 0; --j) {
                    dp[i][j] = (dp[i][j] + dp[i - gr][Math.max(0, j - prof)]) % MOD;
                }
            }
        }

        int res = 0;
        for (int i = 0; i <= n; ++i) {
            res = (res + dp[i][minProfit]) % MOD;
        }
        return res;
    }
}
