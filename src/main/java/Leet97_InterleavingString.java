public class Leet97_InterleavingString {
    public boolean isInterleave(String s1, String s2, String s3) {
        char[] c1 = s1.toCharArray(), c2 = s2.toCharArray(), c3 = s3.toCharArray();

        int len1 = c1.length, len2 = c2.length, len3 = c3.length;

        if (len1 + len2 != len3)
            return false;

        boolean[][] dp = new boolean[len1 + 1][len2 + 1];
        dp[0][0] = true;

        int i, j;

        i = 0;
        for (j = 1; j <= len2 && dp[0][j - 1]; ++j) {
            dp[0][j] = dp[0][j - 1] && c2[j - 1] == c3[j - 1];
        }

        for (i = 1; i <= len1; ++i) {
            for (j = 0; j <= len2; ++j) {
                dp[i][j] = (dp[i - 1][j] && c1[i - 1] == c3[i + j - 1]) || (dp[i][j - 1] && c2[j - 1] == c3[i + j - 1]);
            }
        }

        return dp[len1][len2];
    }
}
