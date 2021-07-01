package mods.graphic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MinSpanTree {

    int find(int[] parents, int father) {
        while (parents[father] > 0) {
            father = parents[father];
        }
        return father;
    }

    public List<Edge> kruskal(Graph input) {
        List<Edge> res = new ArrayList<>();
        int[] parents = new int[input.vertex];

        Arrays.sort(input.edges, Comparator.comparingInt((Edge o) -> o.weight));

        int m, n;
        for (Edge edge : input.edges) {
            m = find(parents, edge.begin);
            n = find(parents, edge.end);
            if (m != n) {
                parents[n] = m;
                res.add(edge);
            }
        }

        return res;
    }

    public List<Edge> prim(int[][] matrix, int start) {
        List<Edge> res = new ArrayList<>();

        int num = matrix.length;

        int[] prims = new int[num];
        int index = 0;
        prims[index++] = start;

        int[] weights = new int[num];
        System.arraycopy(matrix[start], 0, weights, 0, num);
        //  start到自己的距离在邻接图中理应为0，这里确保一下
        weights[start] = 0;

        for (int i = 0; i < num; ++i) {
            if (start == i)
                continue;

            int j = 0, k = 0;
            int min = Integer.MAX_VALUE;

            //  找出未加入最小生成树顶点中权值最小的顶点
            while (j < num) {
                if (weights[j] != 0 && weights[j] < min) {
                    min = weights[j];
                    k = j;
                }
                j++;
            }

            //  k点
            prims[index++] = k;
            weights[k] = 0;

            //  加入结果后更新其它顶点权值
            for (j = 0; j < num; ++j) {
                if (weights[j] != 0 && matrix[k][j] < weights[j])
                    weights[j] = matrix[k][j];
            }
        }

        int sum = 0;
        for (int i = 1; i < index; ++i) {
            int min = Integer.MAX_VALUE;

            for (int j = 0; j < i; ++j) {
                if (matrix[prims[j]][prims[i]] < min) {
                    res.add(new Edge(prims[j], prims[i], matrix[prims[j]][prims[i]]));
                    min = matrix[prims[j]][prims[i]];
                }
            }

            sum += min;
        }

        return res;
    }
}
