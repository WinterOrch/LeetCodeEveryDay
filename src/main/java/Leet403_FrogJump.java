import java.util.HashMap;
import java.util.Map;

public class Leet403_FrogJump {
    public boolean canCross(int[] stones) {
        Map<Integer, Boolean> map = new HashMap<>();
        return helper(stones, 0, 0, map);
    }

    private boolean helper(int[] stones, int pos, int jump, Map<Integer, Boolean> map) {
        int n = stones.length, key = pos | jump << 11;
        if (pos >= n - 1)
            return true;
        if (map.containsKey(key))
            return map.get(key);

        for (int i = pos + 1; i < n; ++i) {
            int dist = stones[i] - stones[pos];
            if (dist < jump - 1)
                continue;
            if (dist > jump + 1) {
                map.put(key, false);
                return false;
            }
            if (helper(stones, i, dist, map)) {
                map.put(key, true);
                return true;
            }
        }

        map.put(key, false);
        return false;
    }

    public static void main(String[] args) {
        Leet403_FrogJump ins = new Leet403_FrogJump();

        int[] stones = {0, 1, 3, 5, 6, 8, 12, 17};
        System.out.println(ins.canCross(stones));
    }
}
