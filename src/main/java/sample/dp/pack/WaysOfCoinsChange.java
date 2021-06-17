package sample.dp.pack;

public class WaysOfCoinsChange {
    /**
     * 类型二
     * 从前i个物品中选一些，组成容量为j的次数
     * created in 17:40 2021/4/19
     */
    public int backPackIV(int[] nums, int target) {
        int[][] dp = new int[nums.length + 1][target + 1];

        dp[0][0] = 1;
        for(int i = 1; i <= nums.length; ++i) {
            for(int j = 0; j <= target; ++j) {
                //当前物品取的个数
                int k = 0;

                while(k * nums[i - 1] <= j) {
                    dp[i][j] += dp[i - 1][j - nums[i - 1] * k];
                    k++;
                }
            }
        }

        return dp[nums.length][target];
    }

    /**
     * 实际上逐行的写法甚至更快
     * created in 18:59 2021/4/19
     */
    public int backPack(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;

        for (int num : nums) {
            for (int j = num; j <= target; ++j)
                dp[j] += dp[j - num];
        }

        return dp[target];
    }

    public static void main(String[] args) {
        int[] money = {1,5,10,20,50,100};
        int target = 200;

        WaysOfCoinsChange ins = new WaysOfCoinsChange();

        long startTime = System.currentTimeMillis();
        System.out.print(ins.backPack(money, target));
        long endTime = System.currentTimeMillis();
        System.out.println("执行时间：" + (endTime - startTime) + "ms");

        startTime = System.currentTimeMillis();
        System.out.print(ins.backPackIV(money, target));
        endTime = System.currentTimeMillis();
        System.out.println("执行时间：" + (endTime - startTime) + "ms");
    }
}
