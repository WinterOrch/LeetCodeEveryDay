import java.util.Arrays;

public class Leet64_MinimumPathSum {
    public int minPathSum(int[][] grid) {
        int[] dp = Arrays.copyOf(grid[0], grid[0].length);

        for(int i = 1; i < grid[0].length; ++i) {
            dp[i] += dp[i - 1];
        }

        for(int i = 1; i < grid.length; ++i) {
            dp[0] += grid[i][0];

            for(int j = 1; j < grid[0].length; ++j) {
                dp[j] = Math.min(dp[j - 1], dp[j]) + grid[i][j];
            }
        }

        return dp[grid[0].length - 1];
    }
}
