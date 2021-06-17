import java.util.*;

public class Leet241_DifferentWaysToAddParentheses {
    public List<Integer> diffWaysToCompute(String expression) {
        char[] chars = expression.toCharArray();
        int len = chars.length;

        int[] nums = new int[len / 2 + 1];
        int nNums = 0;
        char[] signs = new char[len / 2];
        int nSigns = 0;

        for (int i = 0; i < len; ++i) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                nums[nNums++] = chars[i] - '0';
                while (i < len - 1 && chars[i + 1] >= '0' && chars[i + 1] <= '9') {
                    ++i;
                    nums[nNums - 1] = 10 * nums[nNums - 1] + chars[i] - '0';
                }
            } else {
                signs[nSigns++] = chars[i];
            }
        }


        List<Integer> ans;
        if (nNums == 1) {
            ans = new ArrayList<>();
            ans.add(nums[0]);
            return ans;
        }

        Map<Integer, List<Integer>> memo = new HashMap<>();

        ans = helper(nums, signs, 0, nSigns - 1, memo);

        if (ans != null) {
            return ans;
        } else {
            return new ArrayList<>();
        }
    }

    private List<Integer> helper(int[] nums, char[] signs, int start, int end,
                                 Map<Integer, List<Integer>> memo) {
        if (start > end) {
            return null;
        }

        int stateKey = (start << 16) | end;
        if (memo.containsKey(stateKey)) {
            return memo.get(stateKey);
        }

        List<Integer> res = new ArrayList<>();
        for (int i = start; i <= end; ++i) {
            List<Integer> left = helper(nums, signs, start, i - 1, memo),
                          right = helper(nums, signs, i + 1, end, memo);

            if (left != null && right != null) {
                // i != start && i != end
                for (int leftNum : left) {
                    for (int rightNum : right) {
                        res.add(calc(signs[i], leftNum, rightNum));
                    }
                }
            } else if (left != null) {
                // i == end (right edge)
                for (int leftNum : left) {
                    res.add(calc(signs[i], leftNum, nums[i + 1]));
                }
            } else if (right != null) {
                // i == start (left edge)
                for (int rightNum : right) {
                    res.add(calc(signs[i], nums[i], rightNum));
                }
            } else {
                // start == end
                res.add(calc(signs[start], nums[start], nums[end + 1]));
            }

        }
        memo.put(stateKey, res);
        return res;
    }

    private int calc(char sign, int one, int two) {
        switch (sign) {
            case '+':
                return one + two;
            case '-':
                return one - two;
            case '*':
                return one * two;
            case '/':
                return one / two;
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        Leet241_DifferentWaysToAddParentheses ins = new Leet241_DifferentWaysToAddParentheses();
        System.out.println(ins.diffWaysToCompute("2*3-4*5"));
    }
}
