import java.util.*;

public class Leet743_Delay {
    public int networkDelayTime(int[][] times, int n, int k) {
        int res = 0;

        int[][] g = new int[n][n];
        for(int i = 0; i < n; ++i)
            Arrays.fill(g[i], Integer.MAX_VALUE);

        for(int[] edge : times) {
            g[edge[0] - 1][edge[1] - 1] = edge[2];
        }

        int[] output = dijkstra(k - 1, n, g);

        for(int p : output) {
            if(p > res)
                res = p;
        }

        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private int[] dijkstra(int src, int length, int[][] times) {
        int[] min = new int[length];
        Arrays.fill(min, Integer.MAX_VALUE);
        min[src] = 0;
        boolean[] vis = new boolean[length];

        PriorityQueue<QNode> minHeap = new PriorityQueue<>(2 * length,
                Comparator.comparing(o -> o.w));
        minHeap.offer(new QNode(src, 0));

        QNode nTemp;

        while(!minHeap.isEmpty()) {
            nTemp = minHeap.poll();
            int u = nTemp.v;

            if(vis[u])
                continue;

            vis[u] = true;

            for(int i = 0; i < length; ++i) {
                if(Integer.MAX_VALUE != times[u][i]) {
                    int w = times[u][i];

                    if(!vis[i] && min[i] > min[u] + w) {
                        min[i] = min[u] + w;
                        minHeap.offer(new QNode(i, min[i]));
                    }
                }
            }
        }

        return min;
    }

    class QNode {
        int v;
        int w;

        QNode(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    public static void main(String[] args) {
        Leet743_Delay ins = new Leet743_Delay();

        int[][] times = {{2,1,1},{2,3,1},{3,4,1}};

        System.out.println(ins.networkDelayTime(times, 4, 2));
    }
}
