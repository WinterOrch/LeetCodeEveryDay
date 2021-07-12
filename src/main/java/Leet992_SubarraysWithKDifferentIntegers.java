import java.util.HashMap;
import java.util.Map;

public class Leet992_SubarraysWithKDifferentIntegers {
    public int subarraysWithKDistinct(int[] nums, int k) {
        return helper(nums, k) - helper(nums, k - 1);
    }

    private int helper(int[] nums, int k) {
        int n = nums.length, res = 0, left = 0;
        int[] numCnt = new int[nums.length];
        for (int i = 0; i < n; ++i) {
            if (numCnt[nums[i] - 1]++ == 0) {
                --k;
            }
            while (k < 0) {
                if (--numCnt[nums[left] - 1] == 0) {
                    ++k;
                }
                ++left;
            }
            res += i - left + 1;
        }
        return res;
    }

    public static void main(String[] args) {
        Leet992_SubarraysWithKDifferentIntegers ins = new Leet992_SubarraysWithKDifferentIntegers();
        System.out.println(ins.subarraysWithKDistinct(new int[]{1, 2, 1, 2, 3}, 2));
    }
}
