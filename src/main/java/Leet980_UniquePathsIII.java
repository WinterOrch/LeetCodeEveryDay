public class Leet980_UniquePathsIII {

    public int uniquePathsIII(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int xStart = 0, yStart = 0;

        int target = 0;
        for(int i = 0; i < m; ++i) {
            for(int j = 0; j < n; ++j) {
                if(0 == grid[i][j])
                    target++;
                else if(1 == grid[i][j]) {
                    xStart = i;
                    yStart = j;
                }
            }
        }

        return dpHelper(xStart, yStart, grid, target);
    }

    private int dpHelper(int i, int j, int[][] grid, int target) {
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] < 0)
            return 0;

        if(2 == grid[i][j]) {
            if(target == 0)
                return 1;
            return 0;
        }

        int res = 0;

        grid[i][j] = -1;
        res += dpHelper(i - 1, j, grid, target - 1);
        res += dpHelper(i, j + 1, grid, target - 1);
        res += dpHelper(i + 1, j, grid, target - 1);
        res += dpHelper(i, j - 1, grid, target - 1);
        grid[i][j] = 0;

        return res;
    }
}
