import java.util.ArrayDeque;
import java.util.Deque;

public class Leet1190_ReverseSubstringsBetweenEachPairOfParentheses {
    public String reverseParentheses(String s) {
        if (s.length() <= 1)
            return s;

        char[] chars = s.toCharArray();

        StringBuilder res = new StringBuilder();
        helper(chars, 0, chars.length, res, false);
        return res.toString();
    }

    private void helper(char[] chars, int start, int end, StringBuilder res, boolean toReverse) {
        if (start >= end)
            return;

        int parenthesesFlag = 0;
        int i = toReverse ? end - 1 : start;
        if (toReverse) {
            while (i >= start) {
                if (chars[i] == ')') {
                    parenthesesFlag = 1;
                    break;
                }
                res.append(chars[i]);
                --i;
            }

            if (parenthesesFlag == 1) {
                int nxtEnd = i;
                --i;

                while (i >= start) {
                    if (chars[i] == ')') {
                        ++parenthesesFlag;
                    } else if (chars[i] == '(') {
                        --parenthesesFlag;
                        if (0 == parenthesesFlag)
                            break;
                    }
                    --i;
                }

                int nxtStart = i + 1;
                helper(chars, nxtStart, nxtEnd, res, false);
                helper(chars, start, nxtStart - 1, res, true);
            }

        } else {
            while (i < end) {
                if (chars[i] == '(') {
                    parenthesesFlag = 1;
                    break;
                }
                res.append(chars[i]);
                ++i;
            }

            if (parenthesesFlag == 1) {
                int nxtStart = i = i + 1;
                while (i < end) {
                    if (chars[i] == '(') {
                        ++parenthesesFlag;
                    } else if (chars[i] == ')') {
                        --parenthesesFlag;
                        if (0 == parenthesesFlag)
                            break;
                    }
                    ++i;
                }

                int nxtEnd = i;
                helper(chars, nxtStart, nxtEnd, res, true);
                helper(chars, nxtEnd + 1, end, res, false);
            }
        }
    }

    /**
     * 使用双端队列的方案
     * created in 11:23 2021/5/26
     */
    public String reverseParentheses_2(String s) {
        int n = s.length();
        char[] cs = s.toCharArray();
        Deque<Character> d = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            char c = cs[i];

            //  每次遇到后括号，向前到最后一个左括号全部倒装一次
            if (c == ')') {
                StringBuilder path = new StringBuilder();
                while (!d.isEmpty()) {
                    if (d.peekLast() != '(') {
                        path.append(d.pollLast());
                    } else {
                        d.pollLast();
                        for (int j = 0; j < path.length(); j++) {
                            d.addLast(path.charAt(j));
                        }
                        break;
                    }
                }
            } else {
                d.addLast(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!d.isEmpty()) sb.append(d.pollFirst());
        return sb.toString();
    }

    public static void main(String[] args) {
        Leet1190_ReverseSubstringsBetweenEachPairOfParentheses ins = new Leet1190_ReverseSubstringsBetweenEachPairOfParentheses();
        System.out.println(ins.reverseParentheses("a(bcdefghijkl(mno)p)q"));
    }
}
