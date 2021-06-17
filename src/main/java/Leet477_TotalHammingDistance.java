public class Leet477_TotalHammingDistance {
    public int totalHammingDistance(int[] nums) {
        int maxLeading = 32;
        for (int num : nums) {
            maxLeading = Math.min(maxLeading, Integer.numberOfLeadingZeros(num));
        }
        int maxBitLength = 32 - maxLeading;
        int n = nums.length;

        int res = 0;

        int mask = 0x01;
        for (int i = 0; i < maxBitLength; ++i) {
            int cnt = 0;

            for (int num : nums) {
                if ((num & mask) > 0) {
                    ++cnt;
                }
            }

            res += cnt * (n - cnt);
            mask <<= 1;
        }

        return res;
    }
}
