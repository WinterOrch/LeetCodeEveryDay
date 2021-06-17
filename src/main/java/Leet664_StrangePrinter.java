public class Leet664_StrangePrinter {
    public int strangePrinter(String s) {
        if (s.length() == 1)
            return 1;

        char[] arr = s.toCharArray();
        int len = arr.length;

        int[][] dp = new int[len][len];

        for (int i = len - 1; i >= 0; --i) {
            for (int j = i; j < len; ++j) {
                if (i == j) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = 1 + dp[i + 1][j];
                    for (int k = i + 1; k <= j; ++k) {
                        if (arr[k] == arr[i]) {
                            dp[i][j] = Math.min(dp[i][j], dp[i + 1][k - 1] + dp[k][j]);
                        }
                    }
                }
            }
        }

        return dp[0][len - 1];
    }
}
