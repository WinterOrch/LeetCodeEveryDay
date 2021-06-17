public class Leet221_MaximalSquare {
    /**
     * DP表存储以当前点为右下角的最大正方形
     * created in 15:15 2021/4/14
     */
    public int maximalSquare(char[][] matrix) {
        int width = matrix[0].length, height = matrix.length;

        int[][] dp = new int[height][width];

        int res = 0;
        for(int i = 0; i < width; ++i) {
            if(matrix[0][i] == '1') {
                if(res == 0)
                    res = 1;
                dp[0][i] = 1;
            }
        }
        for(int i = 0; i < height; ++i) {
            if(matrix[i][0] == '1') {
                if(res == 0)
                    res = 1;
                dp[i][0] = 1;
            }
        }

        for(int i = 1; i < height; ++i) {
            for(int j = 1; j < width; ++j) {
                if(matrix[i][j] == '1') {
                    if(matrix[i - 1][j - 1] == '0') {
                        dp[i][j] = 1;
                    }else {
                        //int max = dp[i - 1][j - 1] + 1;
                        //int k;
                        //for(k = 1; k < max; ++k) {
                        //    if(matrix[i][j - k] == '0' || matrix[i - k][j] == '0')
                        //        break;
                        //}
                        //dp[i][j] = k;
                        dp[i][j] = Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1])) + 1;
                    }
                    res = Math.max(res, dp[i][j]);
                }
            }
        }

        return res * res;
    }

    public static void main(String[] args) {
        char[][] square = {{'1','0','1','0','0'},
                          {'1','0','1','1','1'},
                          {'1','1','1','1','1'},
                          {'1','0','0','1','0'}};

        Leet221_MaximalSquare ins = new Leet221_MaximalSquare();

        System.out.println(ins.maximalSquare(square));
    }
}
