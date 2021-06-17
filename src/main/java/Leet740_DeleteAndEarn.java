public class Leet740_DeleteAndEarn {
    public int deleteAndEarn(int[] nums) {
        int[] neighbors = new int[10002];

        int max = 0;
        for (int num : nums) {
            neighbors[num]++;

            if(num > max)
                max = num;
        }

        int[] dp = new int[max + 1];
        dp[1] = neighbors[1];
        for (int i = 2; i <= max; ++i) {
            if (neighbors[i] == 0)
                dp[i] = dp[i - 1];
            else {
                dp[i] = Math.max(dp[i - 2] + neighbors[i] * i, dp[i - 1]);
            }
        }

        return dp[max];
    }

    public static void main(String[] args) {
        int[] nums = {2, 2, 3, 3, 3, 4};
        Leet740_DeleteAndEarn ins = new Leet740_DeleteAndEarn();

        System.out.println(ins.deleteAndEarn(nums));
    }
}
