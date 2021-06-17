import java.util.Arrays;

public class Leet1310_XorQueriesOfASubarray {
    public int[] xorQueries(int[] arr, int[][] queries) {
        SegmentTree segmentTree = new SegmentTree(arr);

        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; ++i) {
            res[i] = segmentTree.sumRange(queries[i][0], queries[i][1]);
        }

        return res;
    }

    static class SegmentTree {
        private final int n;
        private final int[] tree;

        SegmentTree(int[] nums) {
            this.n = nums.length;
            tree = new int[this.n * 2];

            this.buildTree(nums);
        }

        private void buildTree(int[] nums) {
            if (2 * this.n - n >= 0) {
                System.arraycopy(nums, 0, this.tree, n, 2 * this.n - n);
            }

            for (int i = n - 1; i > 0; --i) {
                this.tree[i] = this.tree[i * 2] ^ this.tree[i * 2 + 1];
            }
        }

        private int sumRange(int i, int j) {
            int sum = 0;
            for (i += n, j += n; i <= j; i /= 2, j /= 2) {
                if ((i & 1) == 1)
                    sum ^= tree[i++];
                if ((j & 1) == 0)
                    sum ^= tree[j--];
            }
            return sum;
        }
    }

    public static void main(String[] args) {
        Leet1310_XorQueriesOfASubarray ins = new Leet1310_XorQueriesOfASubarray();

        int[] arr = {1,3,4,8};
        int[][] queries = {{0,1},{1,2},{0,3},{3,3}};

        System.out.println(Arrays.toString(ins.xorQueries(arr, queries)));
    }
}
