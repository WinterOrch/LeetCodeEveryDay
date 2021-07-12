import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Leet981_TimeBasedKeyValueStore {
    class Node {
        String k, v;

        int t;

        Node (String k, String v, int t) {
            this.k = k;
            this.v = v;
            this.t = t;
        }
    }

    Map<String, List<Node>> map = new HashMap<>();

    public void set(String key, String value, int t) {
        List<Node> list = map.getOrDefault(key, new ArrayList<>());
        list.add(new Node(key, value, t));
        map.put(key, list);
    }

    public String get(String k, int t) {
        List<Node> list = map.get(k);
        if (list == null || list.isEmpty()) {
            return "";
        }

        int n = list.size();
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (list.get(mid).t <= t) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return list.get(r).t <= t ? list.get(r).v : "";
    }
}
