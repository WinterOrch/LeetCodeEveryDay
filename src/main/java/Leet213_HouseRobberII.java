import java.util.Arrays;

public class Leet213_HouseRobberII {
    public int rob(int[] nums) {
        if(nums.length == 1)
            return nums[0];

        int[] dp = new int[nums.length + 1];
        dp[2] = nums[1];

        for(int i = 3; i <= nums.length; ++i) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }

        int res = dp[nums.length];

        dp = new int[nums.length + 1];
        Arrays.fill(dp, 0);
        dp[1] = nums[0];

        for(int i = 2; i < nums.length; ++i) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }

        res = Math.max(res, dp[nums.length - 1]);

        return res;
    }

    public static void main(String[] args) {
        int[] num = {4,1,2,7,5,3,1};
        Leet213_HouseRobberII ins = new Leet213_HouseRobberII();

        System.out.println(ins.rob(num));
    }
}
