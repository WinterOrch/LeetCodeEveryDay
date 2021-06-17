public class Leet63_UniquePathsII {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int n = obstacleGrid[0].length;

        int[] dp = new int[n];
        dp[0] = 1;

        for (int[] anObstacleGrid : obstacleGrid) {
            for (int j = 0; j < n; j++) {
                if (1 == anObstacleGrid[j])
                    dp[j] = 0;
                else if (j > 0)
                    dp[j] += dp[j - 1];
            }
        }

        return dp[n - 1];
    }
}
