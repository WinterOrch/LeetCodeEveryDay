package lintcode;

/**
 * 从大到小的记忆化搜索
 * 合并i至j，因为每次只能合并相邻两点，所以对所有i,j都有j-i-1个中间点可以选择
 * created in 21:03 2021/4/21
 */
public class StoneGame {
    public int getMinRes(int[] input) {
        if(input == null || input.length == 0) {
            return 0;
        }

        int[][] dp = new int[input.length][input.length];
        int[][] vi = new int[input.length][input.length];

        int[][] sum = new int[input.length][input.length];
        for(int i = 0; i < input.length; ++i) {
            sum[i][i] = input[i];
            for(int j = i + 1; j < input.length; ++j) {
                sum[i][j] = sum[i][j - 1] + input[j];
            }
        }

        return search(0, input.length - 1, dp, vi, sum);
    }

    private int search(int l, int r, int[][] dp, int[][] vis, int[][] sum) {
        if(vis[l][r] == 1)
            return dp[l][r];
        if(l == r) {
            vis[l][r] = 1;
            return dp[l][r];
        }

        dp[l][r] = Integer.MAX_VALUE;
        for(int k = l; k < r; ++k) {
            dp[l][r] = Math.min(dp[l][r],
                    search(l, k, dp, vis, sum) + search(k + 1, r, dp, vis, sum) + sum[l][r]);
        }
        vis[l][r] = 1;
        return dp[l][r];
    }
}
