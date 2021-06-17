public class Leet402_RemoveKDigits {
    /**
     * 方法一、贪心+单调栈
     * 如果用Deque，char的封装类由于不能直接比较大小，会需要频繁进行拆装箱
     * 所以这里直接用数组表示栈了，也不复杂
     * created in 15:07 2021/5/9
     */
    public String removeKdigits(String num, int k) {
        if (k >= num.length())
            return "0";
        else if (k == 0)
            return num;

        char[] nums = num.toCharArray();

        char[] stack = new char[nums.length];
        int top = -1;

        for (char c : nums) {
            while (top != -1 && k > 0 && stack[top] > c) {
                top--;
                k--;
            }
            stack[++top] = c;
        }

        top -= k;

        int offset = 0;
        for (int i = 0; i <= top; ++i) {
            if ('0' == stack[i])
                ++offset;
            else
                break;
        }

        if (offset > top)
            return "0";
        else
            return new String(stack, offset, top - offset + 1);
    }

    /**
     * 方法二、递归写法
     * 这里和上面其实思路是完全一样的
     * created in 15:08 2021/5/9
     */
    public String removeKdigits_2(String num, int k) {
        if (k >= num.length())
            return "0";
        else if (k == 0)
            return num;

        char[] nums = num.toCharArray();

        char[] res = new char[nums.length];
        int top = helper(nums, 0, k, res, -1);

        int offset = 0;
        for (int i = 0; i <= top; ++i) {
            if ('0' == res[i])
                ++offset;
            else
                break;
        }

        if (offset > top)
            return "0";
        else
            return new String(res, offset, top - offset + 1);
    }

    private int helper(char[] part, int offset, int x, char[] stack, int top) {
        if (part.length - offset == x) {
            return top;
        }

        if (0 == x) {
            for (int i = offset; i < part.length; ++i) {
                stack[++top] = part[i];
            }
            return top;
        }


        int idx = -1;
        char min = Character.MAX_VALUE;
        for (int i = offset; i <= offset + x; ++i) {
            if (part[i] < min) {
                min = part[i];
                idx = i;
            }
        }

        stack[++top] = min;

        return helper(part, idx + 1, x - idx + offset, stack, top);
    }

    public static void main(String[] args) {
        Leet402_RemoveKDigits ins = new Leet402_RemoveKDigits();

        System.out.println(ins.removeKdigits_2("112", 1));
    }
}