package mods.structure.tree;

public class SegmentTree {
    private int n;
    private int[] tree;

    public SegmentTree(int[] nums) {
        this.n = nums.length;
        tree = new int[this.n * 2];

        this.buildTree(nums);
    }

    private void buildTree(int[] nums) {
        if (2 * this.n - n >= 0)
            System.arraycopy(nums, 0, this.tree, n, 2 * this.n - n);

        for (int i = n - 1; i > 0; --i) {
            this.tree[i] = this.tree[i * 2] + this.tree[i * 2 + 1];
        }
    }

    void update(int i, int val) {
        this.tree[i += n] = val;
        while (i > 0) {
            this.tree[i / 2] = this.tree[i] + this.tree[i ^ 1];
            i /= 2;
        }
    }

    int sumRange(int i, int j) {
        int sum = 0;
        for (i += n, j += n; i <= j; i /= 2, j /= 2) {
            if ((i & 1) == 1)
                sum += tree[i++];
            if ((j & 1) == 0)
                sum += tree[j--];
        }
        return sum;
    }
}
