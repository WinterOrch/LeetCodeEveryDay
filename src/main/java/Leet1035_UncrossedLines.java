public class Leet1035_UncrossedLines {
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int len_1 = nums1.length, len_2 = nums2.length;

        int[][] dp = new int[len_1 + 1][len_2 + 1];

        for (int i = 1; i <= len_1; ++i) {
            for (int j = 1; j <= len_2; ++j) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[len_1][len_2];
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 4, 2};
        int[] nums2 = {1, 2, 4};

        Leet1035_UncrossedLines ins = new Leet1035_UncrossedLines();

        System.out.println(ins.maxUncrossedLines(nums1, nums2));
    }
}
