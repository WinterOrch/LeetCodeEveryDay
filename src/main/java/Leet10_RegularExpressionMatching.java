public class Leet10_RegularExpressionMatching {
    /**
     * 递归，能过但耗时有点长
     * created in 16:20 2021/4/16
     */
    public boolean isMatch(String s, String p) {
        if(p.isEmpty()) {
            return s.isEmpty();
        }

        if(p.length() == 1) {
            return p.equals(s) || (p.charAt(0) == '.' && s.length() == 1);
        }

        if(p.charAt(1) != '*') {
            return !s.isEmpty()
                    && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.')
                    && isMatch(s.substring(1), p.substring(1));
        }

        if(p.charAt(1) == '*') {
            return matchStar(s, p.substring(2), p.charAt(0));
        }else
            return false;
    }

    private boolean matchStar(String s, String p, char c) {
        if(isMatch(s, p))
            return true;
        while(!s.isEmpty() && (s.charAt(0) == c || c == '.')) {
            s = s.substring(1);
            if(isMatch(s, p))
                return true;
        }
        return false;
    }

    /**
     * DP
     * created in 16:20 2021/4/16
     */
    public boolean isMatchWithDP(String s, String p) {
        char[] arr1 = s.toCharArray(), arr2 = p.toCharArray();
        int sLen = arr1.length, pLen = arr2.length;

        // dp[i][j]记录s中前i位与p前j位是否匹配
        boolean[][] dp = new boolean[sLen + 1][pLen + 1];
        dp[0][0] = true;

        for(int i = 0; i <= sLen; ++i) {
            for(int j = 1; j <= pLen; ++j) {

                if (j > 1 && arr2[j - 1] == '*') {
                    dp[i][j] = dp[i][j - 2] || (i > 0 && (arr1[i - 1] == arr2[j - 2] || arr2[j - 2] == '.')
                                                && dp[i - 1][j]);
                } else {
                    dp[i][j] = i > 0 && dp[i - 1][j - 1]
                                     && (arr1[i - 1] == arr2[j - 1] || arr2[j - 1] == '.');
                }
            }
        }

        return dp[sLen][pLen];
    }

    public static void main(String[] args) {
        String s = "aasdfasdfasdfasdfas";
        String p = "aasdf.*asdf.*asdf.*asdf.*s";

        Leet10_RegularExpressionMatching ins = new Leet10_RegularExpressionMatching();

        System.out.println(ins.isMatchWithDP(s, p));
    }
}
