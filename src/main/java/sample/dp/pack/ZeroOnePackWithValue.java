package sample.dp.pack;

public class ZeroOnePackWithValue {
    /**
     * 类型一
     * 有限背包，求最大价值
     * created in 17:52 2021/4/19
     */
    private int[] weight = {0, 2, 3, 4, 5};
    private int[] value = {0, 3, 4, 5, 6};

    private int bagSize = 8;
    private int[][] dp = new int[weight.length][bagSize + 1];
    private int[] res = new int[weight.length];

    /**
     * 动态规划找到相应背包大小下能取得的最大价值
     *  j：背包大小
     *  i：当前行考虑的物品
     * created in 17:43 2021/3/29
     */
    private void dp() {
        for(int i = 1; i < weight.length; ++i) {
            for(int j = 1; j <= bagSize; ++j) {
                if(j < weight[i])
                    dp[i][j] = dp[i - 1][j];
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
            }
        }
    }

    /**
     * 回溯动态规划表以确定最优方案中被选择的物品
     *  如果选择了当前物品，DP表上同一背包大小的上一行位置价值应当有所不同
     * created in 17:54 2021/3/29
     */
    private void traceback(int i, int j) {
        if(i >= 0) {
            if(dp[i][j] == dp[i - 1][j]) {
                res[i] = 0;
                traceback(i - 1, j);
            }else if(j - weight[i] >= 0 && dp[i][j] == dp[i - 1][j - weight[i]] + value[j]) {
                res[i] = 1;
                traceback(i - 1, j - weight[i]);
            }
        }
    }

}
