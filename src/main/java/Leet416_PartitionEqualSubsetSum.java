public class Leet416_PartitionEqualSubsetSum {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num1 : nums) {
            sum += num1;
        }

        if(1 == sum % 2) {
            return false;
        }

        int target = sum / 2;

        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for(int num : nums) {
            for(int i = target; i >= num; --i)
                dp[i] = dp[i] || dp[i - num];
        }

        return dp[target];
    }
}
