public class Leet87_ScrambleString {
    /**
     * 方法1，异构单词方法，超时了
     * created in 14:18 2021/4/16
     */
    public boolean isScramble(String s1, String s2) {
        if(s1.equals(s2))
            return true;

        char[] arr_1 = s1.toCharArray();
        char[] arr_2 = s2.toCharArray();

        return isScramble(arr_1, arr_2, 0, arr_1.length - 1, 0, arr_2.length - 1);
    }

    private boolean isScramble(char[] s1, char[] s2, int start1, int end1, int start2, int end2) {
        int[] dic = new int[26];

        boolean same = true;
        for(int i = start1, j = start2; i <= end1;) {
            if(s1[i] != s2[j])
                same = false;
            ++dic[s1[i++] - 'a'];
            --dic[s2[j++] - 'a'];
        }
        if(same)
            return true;

        for(int i = 0; i < 26; ++i) {
            if(dic[i] != 0)
                return false;
        }

        int len = end1 - start1;
        for(int i = 0; i < len; ++i) {
            if((isScramble(s1, s2, start1, start1 + i, start2, start2 + i)
                    && isScramble(s1, s2, start1 + i + 1, end1, start2 + i + 1, end2)) ||
               (isScramble(s1, s2, start1, start1 + i, end2 - i, end2)
                    && isScramble(s1, s2, start1 + i + 1, end1, start2, end2 - i - 1)))
                return true;
        }

        return false;
    }

    /**
     * 方法2，记忆化搜索
     * created in 14:18 2021/4/16
     */
    public boolean isScramble_2(String s1, String s2) {
        if(s1.equals(s2))
            return true;

        char[] arr_1 = s1.toCharArray();
        char[] arr_2 = s2.toCharArray();

        int n = arr_1.length;
        int[][][] memo = new int[n][n][n + 1];

        return helper(arr_1, arr_2, 0, 0, n, memo);
    }

    private boolean helper(char[] s1, char[] s2, int idx1, int idx2, int len, int[][][] memo) {
        if(len == 0)
            return true;
        if(len == 1) {
            if(s1[idx1] == s2[idx2])
                memo[idx1][idx2][len] = 2;
            else
                memo[idx1][idx2][len] = 1;
        }
        if(memo[idx1][idx2][len] != 0)
            return memo[idx1][idx2][len] == 2;

        //  检查是否为异构，顺便检查一下是不是相等
        int[] dic = new int[26];
        boolean same = true;
        for(int i = idx1, j = idx2; i < idx1 + len;) {
            if(s1[i] != s2[j])
                same = false;
            ++dic[s1[i++] - 'a'];
            --dic[s2[j++] - 'a'];
        }
        if(same) {
            memo[idx1][idx2][len] = 2;
            return true;
        }

        for(int i = 0; i < 26; ++i) {
            if(dic[i] != 0) {
                memo[idx1][idx2][len] = 1;
                return false;
            }
        }


        for(int k = 1; k < len; ++k) {
            if((helper(s1, s2, idx1, idx2, k, memo) &&
                    helper(s1, s2, idx1 + k, idx2 + k, len - k, memo))
                || (helper(s1, s2, idx1, idx2 + len - k, k, memo) &&
                    helper(s1, s2, idx1 + k, idx2, len - k, memo))) {
                memo[idx1][idx2][len] = 2;
                return true;
            }
        }

        memo[idx1][idx2][len] = 1;
        return false;
    }

    public static void main(String[] args) {
        String s1 = "great";
        String s2 = "rgeat";

        Leet87_ScrambleString ins = new Leet87_ScrambleString();

        System.out.println(ins.isScramble_2(s1, s2));
    }
}
