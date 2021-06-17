public class Leet224_BasicCalculator {
    public int calculate(String s) {
        char[] chars = s.toCharArray();
        int res = 0, num = 0, sign = 1, n = chars.length;

        int[] stack = new int[n / 2];
        int top = -1;

        for (char c : chars) {
            if (c >= '0') {
                num = 10 * num + (c - '0');
            } else if (c == '+' || c == '-') {
                res += sign * num;
                num = 0;
                sign = (c == '+') ? 1 : -1;
            } else if (c == '(') {
                stack[++top] = res;
                stack[++top] = sign;
                res = 0;
                sign = 1;
            } else if (c == ')') {
                res += sign * num;
                num = 0;
                res *= stack[top--];
                res += stack[top--];
            }
        }
        res += sign * num;
        return res;
    }
}
