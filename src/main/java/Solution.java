import java.util.HashMap;
import java.util.Map;

/**
 * 这题有一个用HashMap建立字符与位置数组之间映射的方法
 * 位置数组创建是有序的，可以用二分法加快搜索速度
 * created in 15:52 2021/4/4
 */
public class Solution {
    public boolean isSubsequence(String s, String t) {
        int i = 0;
        char[] cT = t.toCharArray();
        for (int j = 0; j < t.length() && i < s.length(); ++j) {
            if (s.charAt(i) == cT[j]) ++i;
        }
        return i == s.length();
    }

    public static void main(String[] args) {
        Solution ins = new Solution();
    }

    public int numRabbits(int[] answers) {
        Map<Integer, Integer> map = new HashMap<>();

        for(int ans : answers) {
            ans += 1;
            map.put(ans + 1, map.getOrDefault(ans, 0) + 1);
        }

        int res = 0;
        for(int num : map.keySet()) {
            int sum = map.get(num);
            int pos = sum / num;
            if(pos * num != sum)
                pos++;

            res += pos * num;
        }

        return res;
    }
}
