public class Leet72_EditDistance {
    public int minDistance(String word1, String word2) {
        char[] arrWord1 = word1.toCharArray();
        char[] arrWord2 = word2.toCharArray();

        int srcLen = arrWord1.length;
        int desLen = arrWord2.length;

        if(srcLen * desLen == 0)
            return srcLen + desLen;

        int[][] dp = new int[srcLen + 1][desLen + 1];

        for(int i = 0; i <= srcLen; ++i)
            dp[i][0] = i;
        for(int i = 0; i <= desLen; ++i)
            dp[0][i] = i;

        for(int i = 1; i <= srcLen; ++i) {
            for(int j = 1; j <= desLen; ++j) {
                if(arrWord1[i - 1] == arrWord2[j - 1])
                    dp[i][j] = dp[i - 1][j - 1];
                else {
                    int insert = dp[i - 1][j - 1];
                    int change_1 = dp[i - 1][j];
                    int change_2 = dp[i][j - 1];

                    if(change_1 < change_2) {
                        if(change_1 < insert)
                            dp[i][j] = change_1 + 1;
                        else
                            dp[i][j] = insert + 1;
                    }else {
                        if(change_2 < insert)
                            dp[i][j] = change_2 + 1;
                        else
                            dp[i][j] = insert + 1;
                    }
                }
            }
        }

        return dp[srcLen][desLen];
    }
}
