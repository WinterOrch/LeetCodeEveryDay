import java.util.ArrayList;
import java.util.List;

public class Leet300_LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        if(nums.length < 2)
            return nums.length;

        List<Integer> dp = new ArrayList<>();

        int len = nums.length;

        dp.add(nums[0]);
        for(int i = 1; i < len; ++i) {
            int left = 0, right = dp.size(), tmp = nums[i];

            // 找的是不小于目标的第一个位置
            while(left < right) {
                int mid = left + (right - left) / 2;

                if(dp.get(mid) < tmp)
                    left = mid + 1;
                else
                    // 这里要求严格递增，因此相同的也替换
                    right = mid;
            }

            if(dp.size() == right) {
                // 比数组内都高
                dp.add(tmp);
            }else {
                dp.set(right, tmp);
            }
        }

        return dp.size();
    }
}
