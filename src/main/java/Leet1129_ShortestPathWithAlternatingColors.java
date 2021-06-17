import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leet1129_ShortestPathWithAlternatingColors {
    public int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {
        List<List<Integer>> redGraph = new ArrayList<>();
        List<List<Integer>> blueGraph = new ArrayList<>();

        for (int i = 0; i < n; ++i) {
            redGraph.add(new ArrayList<>());
            blueGraph.add(new ArrayList<>());
        }
        for (int[] red_edge : red_edges) {
            redGraph.get(red_edge[0]).add(red_edge[1]);
        }
        for (int[] blue_edge : blue_edges) {
            blueGraph.get(blue_edge[0]).add(blue_edge[1]);
        }

        int[][] dp = new int[2][n];
        for (int i = 1; i < n; ++i) {
            dp[0][i] = 2 * n;
            dp[1][i] = 2 * n;
        }

        int[] stackNode = new int[2 * n];
        int[] stackColor = new int[2 * n];
        int overTop = 0;

        stackNode[overTop] = 0;
        stackColor[overTop++] = 0;
        stackNode[overTop] = 0;
        stackColor[overTop++] = 1;

        while (overTop > 0) {
            int cur = stackNode[overTop - 1], color = stackColor[overTop - 1];
            overTop--;

            for (int next : (color == 1) ? redGraph.get(cur) : blueGraph.get(cur)) {
                if (dp[1 - color][next] == 2 * n || 1 + dp[color][cur] < dp[1 - color][next]) {
                    dp[1 - color][next] = 1 + dp[color][cur];
                    stackNode[overTop] = next;
                    stackColor[overTop++] = 1 - color;
                }
            }
        }

        int[] res = new int[n];
        for (int i = 0; i < n; ++i) {
            int val = Math.min(dp[0][i], dp[1][i]);
            res[i] = val == 2 * n ? -1 : val;
        }

        return res;
    }

    public static void main(String[] args) {
        Leet1129_ShortestPathWithAlternatingColors ins = new Leet1129_ShortestPathWithAlternatingColors();
        int n = 5;
        int[][] reds = {{2,2},{0,4},{4,2},{4,3},{2,4},{0,0},{0,1},{2,3},{1,3}}, blues = {{0,4},{1,0},{1,4},{0,0},{4,0}};
        System.out.println(Arrays.toString(ins.shortestAlternatingPaths(n, reds, blues)));

    }
}
