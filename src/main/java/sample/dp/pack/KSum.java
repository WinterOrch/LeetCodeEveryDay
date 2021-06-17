package sample.dp.pack;

public class KSum {

    /**
     * 从 nums[] 中取 k 个元素，使得总和为 target，求选法
     * created in 20:30 2021/4/19
     */
    public int kSum(int[] nums, int k, int target) {
        int[][][] dp = new int[nums.length + 1][k + 1][target + 1];

        for(int i = 0; i < nums.length; ++i) {
            dp[i][0][0] = 1;
        }

        for(int i = 1; i <= nums.length; ++i) {
            for(int j = 1; j <= i && j <= k; ++j) {
                for(int l = 1; l <= target; ++l) {
                    if(nums[i - 1] >= target) {
                        dp[i][j][l] = dp[i - 1][j - 1][l - nums[i - 1]];
                    }
                    dp[i][j][l] += dp[i - 1][j][l];
                }
            }
        }

        return dp[nums.length][k][target];
    }
}
