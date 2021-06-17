public class Leet240_SearchA2dMatrixII {
    public boolean searchMatrix(int[][] matrix, int target) {
        int x = 0, y = matrix.length - 1;
        int n = matrix[0].length;

        while(true) {
            if(x >= n || y < 0)
                return false;
            if(matrix[y][x] == target)
                return true;
            if(matrix[y][x] > target)
                --y;
            else
                ++x;
        }
    }
}
