import java.util.*;

public class Leet1239_MaxLenOfConcatenatedStringArr {
    private final int[] mask = {    0x0001, 0x0002, 0x0004, 0x0008,
                                    0x0010, 0x0020, 0x0040, 0x0080,
                                    0x0100, 0x0200, 0x0400, 0x0800,
                                    0x1000, 0x2000, 0x4000, 0x8000,
                                    0x10000, 0x20000, 0x40000, 0x80000,
                                    0x100000, 0x200000, 0x400000, 0x800000,
                                    0x1000000, 0x2000000, 0x4000000, 0x8000000};

    static Map<Integer, Integer> map = new HashMap<>();

    int n;
    int ans = Integer.MIN_VALUE;
    int[] hash;
    private int max;

    public int maxLength(List<String> arr) {
        n = arr.size();

        HashSet<Integer> set = new HashSet<>();
        for (String s : arr) {
            int val = 0;
            for (char c : s.toCharArray()) {
                int t = c - 'a';
                if ((val & this.mask[t]) != 0) {
                    val = -1;
                    break;
                }
                val |= this.mask[t];
            }
            if (val != -1)
                set.add(val);
        }

        n = set.size();
        if (n == 0) return 0;
        hash = new int[n];

        int idx = 0;
        int total = 0;
        for (Integer i : set) {
            hash[idx++] = i;
            total |= i;
        }
        this.max = Integer.bitCount(total);
        map.put(total, max);

        dfs(0, 0, total);
        return ans;
    }

    void dfs(int u, int cur, int total) {
        if (this.max <= ans)
            return;

        if (u == n) {
            ans = Math.max(ans, getBitCount(cur));
            return;
        }

        if ((hash[u] & cur) == 0) {
            dfs(u + 1, hash[u] | cur, total - (total & hash[u]));
        }
        dfs(u + 1, cur, total);
    }

    int getBitCount(int cur) {
        if (map.containsKey(cur)) {
            return map.get(cur);
        }
        int ans = Integer.bitCount(cur);
        map.put(cur, ans);
        return ans;
    }

    public static void main(String[] args) {
        Leet1239_MaxLenOfConcatenatedStringArr ins = new Leet1239_MaxLenOfConcatenatedStringArr();
        String[] arr = {"un","iq","ue"};
        System.out.println(ins.maxLength(Arrays.asList(arr)));
    }
}
