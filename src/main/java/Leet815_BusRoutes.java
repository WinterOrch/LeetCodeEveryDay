import java.util.*;

public class Leet815_BusRoutes {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }

        int n = routes.length;
        boolean[][] edge = new boolean[n][n];
        Map<Integer, List<Integer>> rec = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int site : routes[i]) {
                List<Integer> list = rec.getOrDefault(site, new ArrayList<>());
                for (int j : list) {
                    edge[i][j] = edge[j][i] = true;
                }
                list.add(i);
                rec.put(site, list);
            }
        }

        // 注释掉的是双向 BFS 方法，同样可过
//        boolean[] vis = new boolean[n];
//        Deque<Integer> src_q, des_q;
//
//        List<Integer> tar = rec.get(source);
//        if (tar == null) {
//            return -1;
//        } else {
//            src_q = new ArrayDeque<>(tar);
//            for (Integer i : tar) {
//                vis[i] = true;
//            }
//        }
//
//        tar = rec.get(target);
//        if (tar == null) {
//            return -1;
//        } else {
//            des_q = new ArrayDeque<>(tar);
//            for (Integer i : tar) {
//                if (vis[i])
//                    return 1;
//                else
//                    vis[i] = true;
//            }
//        }
//
//        int src_steps = 0, des_steps = 1;
//
//        while (!src_q.isEmpty() && !des_q.isEmpty()) {
//            Deque<Integer> front, back;
//            if (src_q.size() > des_q.size()) {
//                front = des_q;
//                back = src_q;
//                ++des_steps;
//            } else {
//                front = src_q;
//                back = des_q;
//                ++src_steps;
//            }
//
//            for (int i = front.size(); i > 0; --i) {
//                int t = front.removeFirst();
//
//                for (int j = 0; j < n; ++j) {
//                    if (edge[t][j]) {
//                        if (!vis[j]) {
//                            vis[j] = true;
//                            front.addLast(j);
//                        } else {
//                            if (back.contains(j)) {
//                                return src_steps + des_steps;
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//        return -1;
        int[] dis = new int[n];
        Arrays.fill(dis, -1);
        Queue<Integer> que = new ArrayDeque<>();
        for (int site : rec.getOrDefault(source, new ArrayList<>())) {
            dis[site] = 1;
            que.offer(site);
        }
        while (!que.isEmpty()) {
            int x = que.poll();
            for (int y = 0; y < n; y++) {
                if (edge[x][y] && dis[y] == -1) {
                    dis[y] = dis[x] + 1;
                    que.offer(y);
                }
            }
        }

        int ret = Integer.MAX_VALUE;
        for (int site : rec.getOrDefault(target, new ArrayList<>())) {
            if (dis[site] != -1) {
                ret = Math.min(ret, dis[site]);
            }
        }
        return ret == Integer.MAX_VALUE ? -1 : ret;
    }

//    public int numBusesToDestination(int[][] routes, int source, int target) {
//        if (source == target)
//            return 0;
//
//        int numBus = routes.length, maxStop = -1;
//        Map<Integer, List<Integer>> stop2bus = new HashMap<>();
//
//        for (int i = 0; i < numBus; ++i) {
//            for (int j : routes[i]) {
//                if (stop2bus.containsKey(j)) {
//                    stop2bus.get(j).add(i);
//                } else {
//                    List<Integer> stop = new ArrayList<>();
//                    stop.add(i);
//                    stop2bus.put(j, stop);
//                }
//
//                if (j > maxStop) {
//                    maxStop = j;
//                }
//            }
//        }
//
//        boolean[] visited = new boolean[maxStop];
//        Deque<Integer> src_q = new ArrayDeque<>();
//        src_q.addLast(source);
//        int src_steps = 0;
//        Deque<Integer> des_q = new ArrayDeque<>();
//        des_q.addLast(target);
//        int des_steps = 0;
//
//        while(!src_q.isEmpty() && !des_q.isEmpty()) {
//            Deque<Integer> front, back;
//
//            if (src_q.size() < des_q.size()) {
//                front = src_q;
//                back = des_q;
//                ++src_steps;
//            } else {
//                front = des_q;
//                back = src_q;
//                ++des_steps;
//            }
//
//            for (int i = front.size(); i > 0; --i) {
//                int t = front.removeFirst();
//                List<Integer> buses = stop2bus.get(t);
//                if (buses == null) {
//                    return -1;
//                }
//                for (int bus : buses) {
//                    if (visited[bus]) {
//                        continue;
//                    }
//                    visited[bus] = true;
//
//                    for (int stop : routes[bus]) {
//                        if (back.contains(stop)) {
//                            return src_steps + des_steps;
//                        }
//                        front.addLast(stop);
//                    }
//                }
//            }
//        }
//        return -1;
//    }

    public static void main(String[] args) {
        int[][] routes = {{1,2,7},{3,6,7}};
        int src = 1, tar = 6;

        Leet815_BusRoutes ins = new Leet815_BusRoutes();
        System.out.println(ins.numBusesToDestination(routes, src, tar));
    }
}
