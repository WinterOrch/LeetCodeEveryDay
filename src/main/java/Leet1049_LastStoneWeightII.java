public class Leet1049_LastStoneWeightII {
    public int lastStoneWeightII(int[] stones) {
        boolean[] dp = new boolean[1501];
        dp[0] = true;

        int sum = 0;
        for (int weight : stones) {
            sum += weight;
            for (int i = Math.min(1500, sum); i >= weight; --i) {
                dp[i] = dp[i] || dp[i - weight];
            }
        }

        for (int i = sum / 2; i >= 0; --i) {
            if (dp[i])
                return sum - 2 * i;
        }
        return 0;
    }
}
