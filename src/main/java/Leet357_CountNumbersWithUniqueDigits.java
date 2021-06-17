public class Leet357_CountNumbersWithUniqueDigits {
    public int countNumbersWithUniqueDigits(int n) {
        if(0 == n)
            return 1;

        if(n > 10)
            n = 10;

        int[] dp = new int[2];
        dp[0] = 1;
        dp[1] = 9;

        int res = 10;

        for(int i = 2; i <= n; ++i) {
            dp[1] *= (10 - i);
            dp[0] *= (11 - i);
            res += dp[1] + (i - 1) * dp[0];
        }

        return res;
    }
}
