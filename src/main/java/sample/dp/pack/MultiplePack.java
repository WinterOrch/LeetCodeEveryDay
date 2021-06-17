package sample.dp.pack;

public class MultiplePack {
    private void zeroOnePack(int[] dp, int value, int weight, int cap) {
        for (int i = cap; i >= weight; --i) {
            if (dp[i - weight] + value > dp[i])
                dp[i] = dp[i - weight] + value;
        }
    }

    private void completePack(int[] dp, int value, int weight, int cap) {
        for (int i = weight; i <= cap; ++i) {
            if (dp[i - weight] + value > dp[i])
                dp[i] = dp[i - weight] + value;
        }
    }

    public int multiplePack(int[] values, int[] weights, int[] nums, int cap) {
        int n = values.length;
        int[] dp = new int[cap + 1];

        for (int i = 0; i < n; ++i) {
            int weight = weights[i], value = values[i], num = nums[i];
            if (weight * num >= cap) {
                //  Treat as Complete Pack
                completePack(dp, value, weight, cap);
            } else {
                int k = 1;
                while (k < num) {
                    //  ZeroOne Pack
                    zeroOnePack(dp, value * k, weight * k, cap);
                    num -= k;
                    k <<= 1;
                }
                //  ZeroOne Pack
                zeroOnePack(dp, value * num, weight * num, cap);
            }
        }

        return dp[cap];
    }
}
