import java.util.ArrayList;
import java.util.List;

public class Leet207_CourseSchedule {
    /**
     * 这个2的设置其实是基于一个事实：
     * 一条分支，如果在一条主枝上能够顺利完成遍历，那在所有枝上都能完成遍历
     * 因为如果在一条枝上无法形成环，那么必定无法在任何枝上形成环
     * 这对于有向图是成立的，但对无向图是不成立的，这里属于前者，因此可以利用
     * created in 10:10 2021/3/25
     */
    List<List<Integer>> edges;
    int[] visited;
    boolean valid = true;

    int[] path;
    int index;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<>();
        for (int i = 0; i < numCourses; ++i) {
            edges.add(new ArrayList<>());
        }
        visited = new int[numCourses];
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
        }
        for (int i = 0; i < numCourses && valid; ++i) {
            if (visited[i] == 0) {
                dfs(i);
            }
        }
        return valid;
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<>();
        for(int i = 0; i < numCourses; ++i)
            edges.add(new ArrayList<>());
        visited = new int[numCourses];
        for(int[] pre : prerequisites)
            edges.get(pre[1]).add(pre[0]);

        path = new int[numCourses];
        index = numCourses - 1; // 从后往前

        for(int i = 0; i < numCourses && valid; ++i) {
            if(visited[i] == 0) {
                dfs(i);
            }
        }

        if(!valid)
            return new int[0];
        else
            return path;
    }

    private void dfsPath(int u) {
        visited[u] = 1;
        for(int v : edges.get(u)) {
            if(visited[v] == 0) {
                dfsPath(v);
                if(!valid) {
                    return;
                }
            }else if(visited[v] == 1) {
                valid = false;
                return;
            }
        }
        visited[u] = 2;
        path[index--] = u;
    }

    public void dfs(int u) {
        visited[u] = 1;
        for (int v: edges.get(u)) {
            if (visited[v] == 0) {
                dfs(v);
                if (!valid) {
                    return;
                }
            } else if (visited[v] == 1) {
                valid = false;
                return;
            }
        }
        visited[u] = 2; // 如果能够在一条枝上完成遍历，也一定能在所有枝上完成遍历

    }

    public static void main(String[] args) {
        int num = 2;
        int[][] input = {{1, 0}, {0, 1}};

        Leet207_CourseSchedule instance = new Leet207_CourseSchedule();

        System.out.println(instance.findOrder(num, input).length);
    }
}
