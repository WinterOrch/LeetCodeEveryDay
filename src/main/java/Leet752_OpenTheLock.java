import java.util.*;

public class Leet752_OpenTheLock {
    int res;
    int[] offset = {1, 9};

    Set<String> deadEnds;

    public int openLock_remastered(String[] dead, String target) {
        if (target.equals("0000"))
            return 0;

        this.deadEnds = new HashSet<>();
        // 虽然这里 IDE 会推荐让 Arrays 来把 String[] 转换成 Collection,但这样做对速度没有好处。
        // IDE 上测出来差别不大，但到了 LeetCode 上居然能让整题躲多耗将近一倍时间，百思不得其解。
        for (String str: dead)
            deadEnds.add(str);

        if (deadEnds.contains("0000"))
            return -1;

        Deque<String> front = new ArrayDeque<>();
        Map<String, Integer> front_map = new HashMap<>();
        Deque<String> back = new ArrayDeque<>();
        Map<String, Integer> back_map = new HashMap<>();

        front.addLast("0000");
        front_map.put("0000", 0);
        back.addLast(target);
        back_map.put(target, 0);

        while (!front.isEmpty() && !back.isEmpty()) {
            if (front.size() < back.size()) {
                if (update(front, front_map, back_map))
                    return this.res;
            } else {
                if (update(back, back_map, front_map))
                    return this.res;
            }
        }
        return -1;
    }

    private boolean update(Deque<String> fs, Map<String, Integer> fs_map, Map<String, Integer> ot_map) {
        String cur = fs.pollFirst();
        char[] chars = cur.toCharArray();
        int step = fs_map.get(cur);

        for (int i = 0; i < 4; ++i) {
            for (int offset : this.offset) {
                char ori = chars[i];
                chars[i] = (char) ('0' + ((chars[i] - '0' + offset) % 10));
                String go = String.valueOf(chars);
                chars[i] = ori;

                if (this.deadEnds.contains(go)) {
                    continue;
                }

                if (fs_map.containsKey(go)) {
                    continue;
                }

                if (ot_map.containsKey(go)) {
                    this.res = step + 1 + ot_map.get(go);
                    return true;
                }

                fs_map.put(go, step + 1);
                fs.addLast(go);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        String[] deadends = {"0201","0101","0102","1212","2002"};
        String target = "0202";

        Leet752_OpenTheLock ins = new Leet752_OpenTheLock();
        System.out.println(ins.openLock_remastered(deadends, target));
    }
}