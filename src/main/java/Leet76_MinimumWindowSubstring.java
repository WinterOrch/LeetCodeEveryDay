import java.util.*;

public class Leet76_MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        if(t.isEmpty())
            return "";

        char[] charArr = s.toCharArray();

        int len = charArr.length;
        int lenT = t.length();

        int[] occ = new int[lenT];
        int[] occExpt = new int[lenT];

        //  这题限制了只能英文字母，用Map其实挺浪费的
        Map<Character, Integer> targets = new HashMap<>();
        for(int i = 0, idx = 0; i < lenT; ++i) {
            if(targets.containsKey(t.charAt(i))) {
                occExpt[targets.get(t.charAt(i))]++;
            }else {
                targets.put(t.charAt(i), idx);
                occExpt[targets.get(t.charAt(i))]++;
                ++idx;
            }
        }

        Set<Map.Entry<Character, Integer>> entrySet = targets.entrySet();

        // 虽然形式上符合双端队列，但实际上用左右两个下标就能表示了
        Deque<Integer> deque = new ArrayDeque<>();
        int minLen = Integer.MAX_VALUE;

        int start = -1, end = -1;

        for(int i = 0; i < len; ++i) {
            Character cur = charArr[i];

            deque.addLast(i);

            if(targets.containsKey(cur)) {
                ++occ[targets.get(cur)];

                while(!deque.isEmpty()) {
                    if(!targets.containsKey(charArr[deque.peekFirst()])) {
                        deque.pollFirst();
                    }else {
                        int idx = targets.get(charArr[deque.peekFirst()]);
                        if(occ[idx] > occExpt[idx]) {
                            deque.pollFirst();
                            --occ[idx];
                        }else if(deque.size() >= lenT) {
                            if(minLen == Integer.MAX_VALUE) {
                                boolean hasAll = true;

                                for(Map.Entry<Character, Integer> entry : entrySet) {
                                    if(occ[entry.getValue()] < occExpt[entry.getValue()]) {
                                        hasAll = false;
                                        break;
                                    }
                                }

                                if(!hasAll)
                                    break;
                            }

                            if(deque.size() < minLen) {
                                minLen = deque.size();

                                start = deque.peekFirst();
                                end = i;
                            }

                            break;
                        }else
                            break;
                    }
                }
            }
        }

        if(minLen != Integer.MAX_VALUE)
            return s.substring(start, end + 1);
        else
            return "";
    }

    public String minWindow2(String s, String t) {
        int[] letterCnt = new int[128];
        int left = 0, cnt = 0, minLeft = -1, minLen = Integer.MAX_VALUE;

        for(int i = 0; i < t.length(); ++i) {
            ++letterCnt[t.charAt(i)];
        }

        for(int i = 0; i < s.length(); ++i) {
            if(--letterCnt[s.charAt(i)] >= 0)
                ++cnt;
            while(cnt == t.length()) {
                if(minLen > i - left + 1) {
                    minLen = i - left + 1;
                    minLeft = left;
                }

                if(++letterCnt[s.charAt(left)] > 0)
                    --cnt;

                ++left;
            }
        }

        return minLeft == -1 ? "" : s.substring(minLeft, minLeft + minLen);
    }

    public static void main(String[] args) {
        String s = "aa";
        String t = "aa";

        Leet76_MinimumWindowSubstring ins = new Leet76_MinimumWindowSubstring();
        System.out.println(ins.minWindow(s, t));
    }
}
