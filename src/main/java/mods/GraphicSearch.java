package mods;

import java.util.Deque;
import java.util.LinkedList;

class GraphicSearch {
    private static boolean[] vis;

    static void bfs(int root, int[][] graph) {
        Deque<Integer> que = new LinkedList<>();

        GraphicSearch.vis = new boolean[graph.length];
        GraphicSearch.vis[root] = true;
        que.offerLast(root);

        int u;
        while(!que.isEmpty()) {
            u = que.pollFirst();

            for(int n : graph[u]) {
                if(!vis[n]) {
                    vis[n] = true;
                    que.offerLast(n);
                }
            }
        }
    }

    static void dfs(int root, int[][] graph) {
        GraphicSearch.vis = new boolean[graph.length];
        dfs_search(root, graph);
    }
    private static void dfs_search(int u, int[][] graph) {
        vis[u] = true;

        for(int n : graph[u]) {
            if(!vis[n])
                dfs_search(n, graph);
        }
    }
}
