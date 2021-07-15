import java.util.Arrays;

public class Leet1818_MinAbsoluteSumDifference {
//    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
//        final int MOD = 1_000_000_007;
//
//        int[] c = new int[nums1.length];
//        System.arraycopy(nums1, 0, c, 0, nums1.length);
//        Arrays.sort(c);
//
//        int sum = 0, maxn = 0;
//        for (int i = 0; i < nums1.length; ++i) {
//            int diff = Math.abs(nums1[i] - nums2[i]);
//            sum = (sum + diff) % MOD;
//
//            maxn = Math.max(maxn, Math.abs(b_search(c, nums2[i]) - nums2[i]));
//        }
//
//        return (sum - maxn + MOD) % MOD;
//    }
//
//    private int b_search(int[] nums, int j) {
//        int l = 0, r = nums.length - 1;
//
//        if (j > nums[r]) {
//            return nums[r];
//        } else if (j < nums[l]) {
//            return nums[l];
//        }
//
//        while (l < r) {
//            int mid = l + (r - l) / 2;
//
//            if (nums[mid] < j) {
//                l = mid + 1;
//            } else {
//                r = mid ;
//            }
//        }
//
//        if (l == 0)
//            return nums[l];
//        else
//            return (nums[l] - j > j - nums[l - 1]) ? nums[l - 1] : nums[l];
//    }

    final int mod = (int)1e9+7;
    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] sorted = nums1.clone();
        Arrays.sort(sorted);
        long sum = 0, max = 0;
        for (int i = 0; i < n; i++) {
            int a = nums1[i], b = nums2[i];
            if (a == b) continue;
            int x = Math.abs(a - b);
            sum += x;
            int l = 0, r = n - 1;
            while (l < r) {
                int mid = l + r + 1 >> 1;
                if (sorted[mid] <= b) l = mid;
                else r = mid - 1;
            }
            int nd = Math.abs(sorted[r] - b);
            if (r + 1 < n)
                nd = Math.min(nd, Math.abs(sorted[r + 1] - b));
            if (nd < x)
                max = Math.max(max, x - nd);
        }
        return (int)((sum - max) % mod);
    }

    private int b_search(int[] nums, int t) {
        int l = 0, r = nums.length - 1;

        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (nums[mid] <= t) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }

        return nums[l];
    }

    public static void main(String[] args) {
        Leet1818_MinAbsoluteSumDifference ins = new Leet1818_MinAbsoluteSumDifference();
        System.out.println(ins.b_search(new int[]{1, 3, 5, 6, 24}, 10));
    }
}
