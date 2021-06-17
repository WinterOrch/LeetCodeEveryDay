public class Leet525_ContiguousArray {
    /**
     * 先把 0 1 换成 -1 1 来看，然后用 HashMap 存k:累加和 v:坐标
     * 如果有相同说明区间上 -1 1 数量相同
     * created in 10:25 2021/6/3
     */
    public int findMaxLength(int[] nums) {
        int res = 0, n = nums.length, sum = 0;

        int[] map = new int[2 * n + 1];
        boolean[] valid = new boolean[2 * n + 1];
        map[n] = -1;
        valid[n] = true;

        for (int i = 0; i < n; ++i) {
            sum += (nums[i] << 1) - 1;

            int tmp = sum + n;

            if (valid[tmp]) {
                res = Math.max(res, i - map[tmp]);
            } else {
                map[tmp] = i;
                valid[tmp] = true;
            }
        }

        return res;
    }
}
