package lintcode;

public class CoinsInALineIII {
    public boolean firstWillWin(int[] values) {
        int n = values.length;

        int[][] dp = new int[n + 1][n + 1];
        boolean[][] flag = new boolean[n + 1][n + 1];

        int sum = 0;
        for(int cur : values) {
            sum += cur;
        }

        return sum < 2 * memorySearch(0, values.length - 1, dp, flag, values);
    }

    private int memorySearch(int left, int right, int[][] dp, boolean[][] flag, int[] values) {
        if(flag[left][right])
            return dp[left][right];

        flag[left][right] = true;

        if(left > right) {
            dp[left][right] = 0;
        }else if(left == right) {
            dp[left][right] = values[left];
        }else if(left + 1 == right) {
            dp[left][right] = Math.max(values[left], values[right]);
        }else {
            int pick_left = Math.min(memorySearch(left + 2, right, dp, flag, values),
                                     memorySearch(left + 1, right - 1, dp, flag, values));
            int pick_right = Math.min(memorySearch(left, right - 2, dp, flag, values),
                                      memorySearch(left + 1, right - 1, dp, flag, values));
            dp[left][right] = Math.max(pick_left, pick_right);
        }

        return dp[left][right];
    }
}
