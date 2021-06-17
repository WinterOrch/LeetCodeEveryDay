public class Leet198_HouseRobber {
    public int rob(int[] nums) {
        int[] dp = new int[3];
        dp[1] = nums[0];

        for(int i = 2; i <= nums.length; ++i) {
            dp[i % 3] = Math.max(dp[(i - 1) % 3], dp[(i - 2) % 3] + nums[i - 1]);
        }

        return dp[nums.length % 3];
    }

    public static void main(String[] args) {
        int[] num = {1, 2, 3, 1};
        Leet198_HouseRobber ins = new Leet198_HouseRobber();

        System.out.println(ins.rob(num));
    }
}
