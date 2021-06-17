public class Leet494_TargetSum {
    public int findTargetSumWays(int[] nums, int target) {
        int depth = 0;
        for (int num : nums) {
            depth += num;
        }

        int diff = depth - target;
        if (diff < 0 || (diff & 0x01) != 0) {
            return 0;
        }

        int neg = diff >> 1;
        int[] dp = new int[neg + 1];
        dp[0] = 1;
        for (int num : nums) {
            for (int j = neg; j >= num; --j) {
                dp[j] += dp[j - num];
            }
        }

        return dp[neg];
    }
}
