package sample;

import java.util.*;

public class Discount {
    public int discount(int[] nums, int m) {
        int[][] count = new int[nums.length][m + 1];
        for (int i = 1; i <= m; i++) {
            if (i <= nums[0]) {
                count[0][i] = nums[0];
            } else {
                count[0][i] = Integer.MAX_VALUE;
            }
        }

        for (int i = 1; i < count.length; i++) {
            for (int j = 0; j <= m; j++) {
                if (j <= nums[i]) {
                    count[i][j] = Math.min(nums[i], count[i - 1][j]);
                } else {
                    count[i][j] = Math.min(nums[i]+ count[i - 1][j - nums[i]], count[i - 1][j]);
                }
            }
        }

        return count[count.length - 1][m];
    }

    public int discountYes(int[] num, int m){
        int n = num.length;
        int sum = 0;
        for (int aNum : num) {
            sum += aNum;
        }
        if(sum < m) {
            return -1;
        }else if(sum == m) {
            return m;
        }

        int[][] dp = new int[n + 1][sum + 1];
        for(int i = m; i <= sum; i++) {
            for(int j = n-1; j >= 0; j--){
                for(int k = 0; k <= i; k++) {
                    if(num[j] > k) {
                        dp[j][k] = dp[j + 1][k];
                    }
                    else {
                        dp[j][k] = Math.max(dp[j + 1][k], dp[j + 1][k - num[j]] + num[j]);
                    }
                }
            }
            if(dp[0][i] == i){
                return i;
            }
        }
        return sum;
    }

    /**
     * 米哈游 - 牛牛的军队
     * created in 10:10 2021/4/19
     */
    public int army(int[][] soldier, int percent) {
        int len = soldier.length;
        //按照睡醒时战力为首要依据，没睡醒时战力为次要依据排序
        //如果有人没睡醒战力比较高，交换一下
        Comparator<int[]> soldierComparator = Comparator
                .comparingInt((int[] o) -> {
                    if(o[0] < o[1]) {
                        int tmp = o[0];
                        o[0] = o[1];
                        o[1] = tmp;
                    }
                    return o[0];
                })
                .thenComparingInt((int[] o) -> o[1]);
        Arrays.sort(soldier, soldierComparator);

        //堆内存当前选择下所有士兵的战力
        TreeSet<Integer> heap = new TreeSet<>();

        //按照所有人都没睡醒载入DP，算初始战力标准
        int[] dp = new int[soldier.length];
        dp[0] = soldier[0][1];
        heap.add(soldier[0][1]);
        for(int i = 1; i < len; ++i) {
            dp[i] = Math.max(dp[i - 1], soldier[i][1]);
            heap.add(soldier[i][1]);
        }

        int minMax = dp[len - 1];
        int standard = minMax * percent / 100;

        int poorSleepIdx = 0;

        //贪心，搜索并提高标准
        for(int i = 0; i < len; ++i) {
            //睡醒都达不到标准的从堆里去除
            if(soldier[i][0] <= standard) {
                heap.remove(soldier[i][1]);
                dp[i] = -1;
                poorSleepIdx = i + 1;
                continue;
            }

            //如果从没睡醒变到睡醒不会提高标准，直接让他睡醒
            if(soldier[i][0] <= minMax && soldier[i][0] > soldier[i][1]) {
                heap.remove(soldier[i][1]);
                heap.add(soldier[i][0]);
                dp[i] = soldier[i][0];
                poorSleepIdx = i + 1;

                //所有[][0]相同的元素，如果要取睡醒状态应当一起取
                while(soldier[i + 1][0] == soldier[i][0]) {
                    heap.remove(soldier[i + 1][1]);
                    heap.add(soldier[i + 1][0]);
                    dp[i + 1] = soldier[i + 1][0];
                    poorSleepIdx = ++i;
                }

            }else if(soldier[i][0] > minMax) {
                //贪心
                int gain = (soldier[i][1] <= standard) ? 1 : 0;

                //没睡醒已经能满足当前战力要求了
                if(gain == 0) {
                    continue;
                }

                int newStandard = soldier[i][1] * percent / 100;

                //计算睡醒带来的增益，包括两部分
                int j = i + 1;
                //向后，相同睡醒战力都是可能的增益
                while(soldier[j][0] == soldier[i][0] && soldier[j][1] <= standard) {
                    j++;
                    gain++;
                }
                j = i - 1;
                //向前，还没睡醒的里面有部分增益
                while(j >= poorSleepIdx && soldier[j][0] > newStandard) {
                    if(soldier[j--][1] <= standard)
                        gain++;
                }

                SortedSet<Integer> tmp = heap.headSet(soldier[i][1] * percent / 100);
                tmp = tmp.tailSet(standard);
                gain -= tmp.size();

                if(gain > 0) {
                    minMax = soldier[i][0];
                    standard = newStandard;

                    while(dp[i - 1] != soldier[i - 1][0] && dp[i - 1] != -1) {
                        i--;
                    }
                }else {
                    //TODO 有可能出现低位不值得贪，高位却值得贪的情况吗（有，所以这里不能break）
                }
            }
        }

        return heap.tailSet(standard).size();
    }

    public static void main(String[] args) {
        Discount ins = new Discount();

        int[] input = {2150, 850, 1010, 980};
        System.out.print(ins.discountYes(input, 4000));
    }
}
