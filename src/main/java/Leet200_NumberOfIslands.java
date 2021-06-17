public class Leet200_NumberOfIslands {
    public int numIslands(char[][] grid) {
        boolean[][] vis = new boolean[grid.length][grid[0].length];
        int res = 0;

        for(int i = 0; i < grid.length; ++i) {
            for(int j = 0; j < grid[0].length; ++j) {
                if (grid[i][j] == '0' || vis[i][j]) continue;
                dfs(i, j, grid, vis);
                ++res;
            }
        }

        return res;
    }

    private void dfs(int xStart, int yStart, char[][] grid, boolean[][] vis) {
        if (xStart < 0 || xStart >= grid.length ||
                yStart < 0 || yStart >= grid[0].length || grid[xStart][yStart] == '0'  || vis[xStart][yStart])
            return;
        vis[xStart][yStart] = true;

        dfs(xStart - 1, yStart, grid, vis);
        dfs(xStart + 1, yStart, grid, vis);
        dfs(xStart, yStart - 1, grid, vis);
        dfs(xStart, yStart + 1, grid, vis);
    }
}
