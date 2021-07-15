package huawei;

import java.util.Scanner;

public class ZooTourism {
    private static int[] father = null;

    public int find(int x) {
        if(father[x] == x) {
            return x;
        }
        return find(father[x]);
    }

    public void union(int a, int b) {
        int root_a = find(a);
        int root_b = find(b);

        if(root_a != root_b)
            father[root_a] = root_b;
    }

    public static void main(String[] args) {
        father = new int[100];

        Scanner in = new Scanner(System.in);

        String line = in.nextLine();

    }
}
