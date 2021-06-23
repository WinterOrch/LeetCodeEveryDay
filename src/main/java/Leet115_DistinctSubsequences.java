public class Leet115_DistinctSubsequences {
    public int numDistinct(String s, String t) {
        char[] src = s.toCharArray(), des = t.toCharArray();
        int n = src.length, m = des.length;
        int[][] dp = new int[m + 1][n + 1];
        dp[0][0] = 1;

        for (int j = 1; j <= n; ++j) {
            dp[0][j] = 1;
        }

        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                dp[i][j] = dp[i][j - 1] + ((src[j - 1] == des[i - 1]) ? dp[i - 1][j - 1] : 0);
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        Leet115_DistinctSubsequences ins = new Leet115_DistinctSubsequences();
        System.out.println(ins.numDistinct("rabbbit", "rabbit"));
    }
}
