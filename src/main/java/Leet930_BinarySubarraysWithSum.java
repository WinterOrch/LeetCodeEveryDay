public class Leet930_BinarySubarraysWithSum {
    /**
     * Sliding Window
     * created in 11:27 2021/7/8
     */
    public int numSubarraysWithSum(int[] nums, int t) {
        int n = nums.length;
        int ans = 0;
        for (int r = 0, l1 = 0, l2 = 0, s1 = 0, s2 = 0; r < n; r++) {
            s1 += nums[r];
            s2 += nums[r];

            while (l1 <= r && s1 > t)
                s1 -= nums[l1++];
            while (l2 <= r && s2 >= t)
                s2 -= nums[l2++];

            ans += l2 - l1;
        }
        return ans;
    }

    /**
     * Prefix Sum
     * created in 11:27 2021/7/8
     */
    public int numWithSum(int[] nums, int t) {
        int n = nums.length;
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }

        int[] map = new int[n + 1];
        map[0] = 1;

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int r = sum[i + 1], l = r - t;

            if (l >= 0) {
                ans += map[l];
            }
            ++map[r];
        }
        return ans;
    }

    public static void main(String[] args) {
        Leet930_BinarySubarraysWithSum ins = new Leet930_BinarySubarraysWithSum();
        System.out.println(ins.numWithSum(new int[]{1, 0, 1, 0, 1}, 2));
    }

}
