public class Leet1143_LongestCommonSubsequence {
    public int longestCommonSubsequence(String text1, String text2) {
        char[] chars1 = text1.toCharArray();
        char[] chars2 = text2.toCharArray();

        int len1 = chars1.length, len2 = chars2.length;

        int[][] dp = new int[len1 + 1][len2 + 1];
        int i, j;
        for (i = 1; i <= len1; ++i) {
            for (j = 1; j <= len2; ++j) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (chars1[i - 1] == chars2[j - 1]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }

        return dp[len1][len2];
    }
}
