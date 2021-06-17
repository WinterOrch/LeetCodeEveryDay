public class Leet65_ValidNumber {
    /**
     * 啥鬼题目，烦的一塌糊涂
     * created in 11:14 2021/6/17
     */
    public boolean isNumber(String s) {
        boolean num = false, numAfterE = true;
        boolean dot = false, exp = false, sign = false;
        char[] chars = s.toCharArray();
        int n = chars.length;

        for (int i = 0; i < n; ++i) {
            if (chars[i] == ' ') {
                if (i < n - 1 && chars[i + 1] != ' ' && (num || dot || exp || sign))
                    return false;
            } else if (chars[i] == '+' || chars[i] == '-') {
                if (i > 0 && chars[i - 1] != 'e' && chars[i - 1] != ' ')
                    return false;
                sign = true;
            } else if (chars[i] >= '0' && chars[i] <= '9') {
                num = true;
                numAfterE = true;
            } else if (chars[i] == '.') {
                if (dot || exp)
                    return false;
                dot = true;
            } else  if (chars[i] == 'e' || chars[i] == 'E') {
                if (exp || !num)
                    return false;
                exp = true;
                numAfterE = false;
            } else {
                return false;
            }
        }

        return num && numAfterE;
    }

    public static void main(String[] args) {
        String input = "1E9";
        Leet65_ValidNumber ins = new Leet65_ValidNumber();
        System.out.println(ins.isNumber(input));
    }
}
