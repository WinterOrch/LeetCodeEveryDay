public class Leet91_DecodeWays {
    public static int numDecodings(String s) {
        if (s.isEmpty() || s.charAt(0) == '0') return 0;
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;

        for (int i = 1; i < dp.length; ++i) {
            dp[i] = dp[i - 1];

            if('0' == s.charAt(i - 1)) {
                if(1 == i || '0' == s.charAt(i - 2) || '2' < s.charAt(i - 2))
                    return 0;
                else
                    dp[i] = 0;
            }

            if(i > 1 && ('1' == s.charAt(i - 2) || '2' == s.charAt(i - 2) && '6' >= s.charAt(i - 1)))
                dp[i] += dp[i - 2];
        }
        return dp[s.length()];
    }

    public static void main(String[] args) {
        String s = "10";
        Integer res = Leet91_DecodeWays.numDecodings(s);

        System.out.println(res);
    }
}
