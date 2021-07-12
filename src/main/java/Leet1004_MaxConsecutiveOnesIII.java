public class Leet1004_MaxConsecutiveOnesIII {
    public int longestOnes(int[] nums, int k) {
        int i = 0, j = 0;
        for (; j < nums.length; ++j) {
            if (nums[j] == 0) {
                --k;
            }
            if (k < 0 && nums[i++] == 0) {
                ++k;
            }
        }
        return j - i;
    }
}
