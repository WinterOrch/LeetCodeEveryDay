import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Leet691_StickersToSpellWord {
    /**
     * 记忆化搜索
     * 每次取一个，从目标中抠除交集，继续搜索剩下部分
     * created in 11:00 2021/4/20
     */
    public int minStickers(String[] stickers, String target) {
        Map<String, int[]> dict = new HashMap<>();
        for(String sticker : stickers) {
            char[] letters = sticker.toCharArray();
            int[] table = new int[26];

            for(char letter : letters) {
                ++table[letter - 'a'];
            }

            dict.put(sticker, table);
        }

        Map<String, Integer> memo = new HashMap<>();
        memo.put("", 0);

        return helper(dict.entrySet(), target, memo);
    }
    private int helper(Set<Map.Entry<String, int[]>> entrySet, String target, Map<String, Integer> memo) {
        if(memo.containsKey(target))
            return memo.get(target);

        int res = Integer.MAX_VALUE;
        int[] cnt = new int[26];

        char[] alpha = target.toCharArray();
        for(char letter : alpha) {
            ++cnt[letter - 'a'];
        }

        for(Map.Entry<String, int[]> entry : entrySet) {
            if(entry.getValue()[alpha[0] - 'a'] == 0)
                continue;

            StringBuilder t = new StringBuilder();
            for(int j = 0; j < 26; ++j) {
                for(int k = 0; k < cnt[j] - entry.getValue()[j]; ++k) {
                    t.append((char)('a' + j));
                }
            }

            int ans = helper(entrySet, t.toString(), memo);
            if(ans != -1) {
                res = Math.min(res, ans + 1);
            }
        }

        if(res == Integer.MAX_VALUE)
            res = -1;
        memo.put(target, res);

        return res;
    }

    /**
     * DP
     * 遍历搜索target的所有子串
     * 在target不长的情况下效率非常高
     * 但当target变长，复杂度将迅速上升
     * created in 11:02 2021/4/20
     */
    public int minStickers2(String[] stickers, String target) {
        int m = 1 << target.length();
        int[] dp = new int[m];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        char[][] table = new char[stickers.length][];
        int len = stickers.length;
        for(int i = 0; i < len; ++i) {
            table[i] = stickers[i].toCharArray();
        }

        char[] targetArr = target.toCharArray();

        for(int i = 0; i < m; ++i) {
            if(dp[i] == Integer.MAX_VALUE)
                continue;

            for(char[] sticker : table) {
                int cur = i;

                for(char letter : sticker) {
                    for(int j = 0; j < targetArr.length; ++j) {
                        if(targetArr[j] == letter && ((cur >> j) & 1) == 0) {
                            cur |= 1 << j;
                            break;
                        }
                    }
                }
                dp[cur] = Math.min(dp[cur], dp[i] + 1);
            }
        }

        return dp[m - 1] == Integer.MAX_VALUE ? -1 : dp[m - 1];
    }

    public static void main(String[] args) {
        String[] stickers = {"with", "example", "science"};
        String target = "thehat";

        Leet691_StickersToSpellWord ins = new Leet691_StickersToSpellWord();

        long startTime = System.currentTimeMillis();
        System.out.print(ins.minStickers(stickers, target));
        long endTime = System.currentTimeMillis();
        System.out.println("执行时间：" + (endTime - startTime) + "ms");

        startTime = System.currentTimeMillis();
        System.out.print(ins.minStickers2(stickers, target));
        endTime = System.currentTimeMillis();
        System.out.println("执行时间：" + (endTime - startTime) + "ms");
    }
}
