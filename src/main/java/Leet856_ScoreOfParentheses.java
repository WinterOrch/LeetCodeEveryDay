public class Leet856_ScoreOfParentheses {

    public int scoreOfParentheses(String s) {
        char[] chars = s.toCharArray();
        int len = chars.length;

        int res = 0;

        int[] stack = new int[len / 2 + 1];
        int top = -1;

        for (char aChar : chars) {
            if (aChar == '(') {
                stack[++top] = 0;
            } else if (aChar == ')') {
                if (stack[top] == 0)
                    stack[top] = 1;
                if (top >= 1) {
                    stack[top - 1] += 2 * stack[top];
                } else {
                    res += stack[0];
                }
                --top;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        Leet856_ScoreOfParentheses ins = new Leet856_ScoreOfParentheses();

        String input = "(())";
        System.out.println(ins.scoreOfParentheses(input));
    }
}
