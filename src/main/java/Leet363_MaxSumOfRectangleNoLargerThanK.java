import java.util.Objects;
import java.util.TreeSet;

public class Leet363_MaxSumOfRectangleNoLargerThanK {
    /**
     * 暴力法
     * created in 10:18 2021/4/22
     */
    public int maxSumSubmatrix(int[][] matrix, int k) {
        if(null == matrix || null == matrix[0])
            return 0;

        int h = matrix.length, w = matrix[0].length;
        int res = Integer.MIN_VALUE;

        int[][] sum = new int[h][w];

        for(int i = 0; i < h; ++i) {
            for(int j = 0; j < w; ++j) {
                int s = matrix[i][j];

                if (i > 0) s += sum[i - 1][j];
                if (j > 0) s += sum[i][j - 1];
                if (i > 0 && j > 0) s -= sum[i - 1][j - 1];

                sum[i][j] = s;

                for(int m = 0; m <= i; ++m) {
                    for(int n = 0; n <= j; ++n) {
                        // 计算子块 (m, n) - (i, j) 的和
                        int sub = sum[i][j];
                        if(m > 0)
                            sub -= sum[m - 1][j];
                        if(n > 0)
                            sub -= sum[i][n - 1];
                        if(m > 0 && n > 0)
                            sub += sum[m - 1][n - 1];

                        if(sub <= k) {
                            res = Math.max(res, sub);
                            System.out.println("RES: " + res + " y: " + m +" - " + i + " x: " + n +" - " + j);
                        }
                    }
                }
            }
        }

        return res;
    }

    /**
     * 2D Kadane
     * created in 10:19 2021/4/22
     */
    public int maxSumSubmatrix2(int[][] matrix, int k) {
        if(null == matrix || null == matrix[0])
            return 0;

        int h = matrix.length, w = matrix[0].length;
        int res = Integer.MIN_VALUE;

        for(int i = 0; i < w; ++i) {
            int[] sum = new int[h];
            for(int j = i; j < w; ++j) {
                for(int l = 0; l < h; ++l) {
                    sum[l] += matrix[l][j];
                }
                int curSum = 0;
                TreeSet<Integer> set = new TreeSet<>();
                set.add(0);

                for(int num : sum) {
                    curSum += num;

                    Integer it = set.ceiling(curSum - k);

                    if(it != null)
                        res = Math.max(res, curSum - it);

                    set.add(curSum);
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[][] in = {{2,2,-1}};

        int k = 0;

        Leet363_MaxSumOfRectangleNoLargerThanK ins = new Leet363_MaxSumOfRectangleNoLargerThanK();
        System.out.println(ins.maxSumSubmatrix2(in, k));
    }
}
