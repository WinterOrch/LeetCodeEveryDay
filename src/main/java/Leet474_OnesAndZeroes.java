public class Leet474_OnesAndZeroes {
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];

        for (String str : strs) {
            char[] chars = str.toCharArray();
            int numOne = 0, numZero = 0;
            for (char c : chars) {
                if (c == '0') {
                    ++numZero;
                } else if (c == '1') {
                    ++numOne;
                }
            }

            for (int i = m; i >= numZero; --i) {
                for (int j = n; j >= numOne; --j) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - numZero][j - numOne] + 1);
                }
            }
        }

        return dp[m][n];
    }
}
