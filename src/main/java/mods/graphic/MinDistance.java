package mods.graphic;

import java.util.*;

/**
 * 计算起点到图上各个结点的最短距离
 */
public class MinDistance {
    /**
     *  SPFA 的想法是从单个结点开始进行更新，将每次更新中发生变动的结点推进队列，
     *  每次对队列中的一个结点进行更新，刷新一遍到所有邻结点的最小路径
     *
     *  其实本身就是 Bellman-ford 算法的队列优化，好处是比堆优化迪杰写起来快很多，
     *  稀疏图上可以用；
     *  坏处是会被卡成 Bellman-ford ，正常请老实 Dij
     */
    public int[] spfa(int vertex, int start, Edge[] edges) {
        int [] minimum = new int[vertex];
        Arrays.fill(minimum, Integer.MAX_VALUE);

        int[][] graph = new int[vertex][vertex];
        for (Edge edge : edges) {
            graph[edge.begin][edge.end] = edge.weight;
            graph[edge.end][edge.begin] = edge.weight;
        }

        Deque<Integer> queue = new LinkedList<>();
        minimum[start] = 0;
        queue.addLast(start);

        while(!queue.isEmpty()) {
            int x = queue.pollFirst();

            for (int i = 0; i < vertex; ++i) {
                if (graph[x][i] > 0) {
                    if (graph[x][i] + minimum[x] < minimum[i]) {
                        minimum[i] = graph[x][i] + minimum[x];

                        if (!queue.contains(i)) {
                            queue.addLast(i);
                        }
                    }
                }
            }
        }

        return minimum;
    }

    /**
     * 小顶堆迪杰斯特拉
     */
    public int[] dijkstra(int vertex, int start, Edge[] edges) {
        int [] minimum = new int[vertex];
        minimum[start] = 0;
        Arrays.fill(minimum, Integer.MAX_VALUE);
        boolean [] vis = new boolean[vertex];

        int[][] graph = new int[vertex][vertex];
        for (Edge edge : edges) {
            graph[edge.begin][edge.end] = edge.weight;
            graph[edge.end][edge.begin] = edge.weight;
        }

        //  小顶堆
        PriorityQueue<Edge> minHeap = new PriorityQueue<>(
                2 * graph.length,
                Comparator.comparing(o -> o.weight)
        );
        minHeap.offer(new Edge(start, start, 0));

        Edge nTemp;
        while(!minHeap.isEmpty()) {
            nTemp = minHeap.poll();

            int u = nTemp.end;

            if(vis[u])
                continue;
            vis[u] = true;

            //  松弛
            for (int i = 0; i < vertex; ++i) {
                if (graph[u][i] > 0) {
                    if (!vis[i] && minimum[i] > minimum[u] + graph[u][i]) {
                        minimum[i] = minimum[u] + graph[u][i];
                        minHeap.offer(new Edge(start, i, minimum[i]));
                    }
                }
            }
        }

        return minimum;
    }
}
