package problems;

import java.util.TreeSet;

@SuppressWarnings("Duplicates")
public class MaximumSubarrayProblem {
    /**
     * Kadane算法
     * 累加，和小于0了就断掉重新从0开始
     * 取最大的一次累加结果进行输出
     * created in 11:08 2021/4/22
     */
    int maxSubarray(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int curSum = 0;

        for(int num : nums) {
            curSum = Math.max(0, curSum + num);
            maxSum = Math.max(maxSum, curSum);
        }

        return maxSum;
    }

    /**
     * 2D Kadane算法
     * Kadane只适用于一维，因此将二维数组转化为一维
     * 通过一个列数组存储 left -> right 的和
     * created in 11:33 2021/4/22
     */
    int maxSubrectangle(int[][] nums) {
        int m = nums.length, n = nums[0].length;
        int maxSum = Integer.MIN_VALUE;

        for(int i = 0; i < m; ++i) {
            int[] press = new int[n];
            for(int j = i; j < m; ++j) {
                for(int k = 0; k < n; ++k) {
                    press[k] += nums[j][k];
                }

                maxSum = Math.max(maxSum, maxSubarray(press));
            }
        }

        return maxSum;
    }

    /**
     * Leet363多加了一个条件不高于t
     * 在2D Kadane基础上加一些内容
     * created in 11:39 2021/4/22
     */
    int maxSubrectangleNoLargerThanK(int[][] nums, int t) {
        int m = nums.length, n = nums[0].length;
        int maxSum = Integer.MIN_VALUE;

        for(int i = 0; i < m; ++i) {
            int[] press = new int[n];
            for(int j = i; j < m; ++j) {
                for(int k = 0; k < n; ++k) {
                    press[k] += nums[j][k];
                }

                int curSum = 0;
                TreeSet<Integer> set = new TreeSet<>();
                set.add(0);

                for(int num : press) {
                    curSum += num;

                    // 找大于等于 curSum - t 的最小元素
                    // 如果 curSum 满足小于 t ，则能找到之前加进来的0
                    Integer it = set.ceiling(curSum - t);
                    if(it != null)
                        maxSum = Math.max(maxSum, curSum - it);

                    set.add(curSum);
                }
            }
        }

        return maxSum;
    }
}
