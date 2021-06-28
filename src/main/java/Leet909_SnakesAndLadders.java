import java.util.LinkedList;
import java.util.Queue;

public class Leet909_SnakesAndLadders {
    public int snakesAndLadders(int[][] board) {
        int n = board.length, res = 0;

        Queue<Integer> q = new LinkedList<>();
        q.add(1);

        boolean[] vis = new boolean[n * n + 1];

        while (!q.isEmpty()) {
            for (int k = q.size(); k > 0; --k) {
                int num = q.poll();

                if (num == n * n) {
                    return res;
                }

                for (int i = 1; i <= 6 && num + i <= n * n; ++i) {
                    int x = (num + i - 1) / n, y = (num + i - 1) % n;
                    if ((x & 0x01) > 0) {
                        y = n - 1 - y;
                    }
                    x = n - 1 - x;

                    int next = board[x][y] == -1 ? (num + i) : board[x][y];
                    if (vis[next])
                        continue;
                    vis[next] = true;
                    q.add(next);
                }
            }
            ++res;
        }
        return -1;
    }
}
