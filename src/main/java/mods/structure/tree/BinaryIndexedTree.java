package mods.structure.tree;

/**
 * 又叫 Fenwick Tree ，是查找和修改复杂度为 O(log n) 的数据结构
 * created in 16:46 2021/5/9
 */
public class BinaryIndexedTree {
    private int[] data, bit;

    public BinaryIndexedTree(int[] nums) {
        this.data = new int[nums.length];
        this.bit = new int[nums.length + 1];

        for (int i = 0; i < nums.length; ++i) {
            this.update(i, nums[i]);
        }
    }

    void update(int i, int val) {
        int diff = val - data[i];
        for (int j = i + 1; j < bit.length; j += (j & -j)) {
            bit[j] += diff;
        }
        data[i] = val;
    }

    /**
     * Get Sum for Indexes from i ~ j
     * created in 16:56 2021/5/9
     */
    int sumRange(int i, int j) {
        return getSum(j + 1) - getSum(i);
    }

    /**
     * Get Sum for Indexes from 0 ~ (i - 1)
     * created in 16:56 2021/5/9
     */
    private int getSum(int i) {
        int res = 0;
        for (int j = i; j > 0; j -= (j & -j)) {
            res += this.bit[j];
        }
        return res;
    }
}
