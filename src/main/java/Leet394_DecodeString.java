public class Leet394_DecodeString {
    public String decodeString(String s) {
        char[] chars = ("1[" + s + "]").toCharArray();
        int len = chars.length;

        StringBuilder[] stringStack = new StringBuilder[len / 3 + 1];
        int[] numberStack = new int[len / 3 + 1];

        int stackTop = -1;

        for (int i = 0; i < len; ++i) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                numberStack[++stackTop] = chars[i] - '0';
                stringStack[stackTop] = new StringBuilder();
                while (chars[++i] != '[') {
                    numberStack[stackTop] = 10 * numberStack[stackTop] + chars[i] - '0';
                }

            } else if (chars[i] == ']') {
                if (stackTop == 0) {
                    continue;
                }
                for (int j = 0; j < numberStack[stackTop]; ++j) {
                    stringStack[stackTop - 1].append(stringStack[stackTop]);
                }
                stringStack[stackTop--] = null;
            } else {
                stringStack[stackTop].append(chars[i]);
            }
        }

        return stringStack[0].toString();
    }

    int i;
    public String decodeString_2(String s) {
        this.i = 0;
        return decode(s.toCharArray());
    }

    private String decode(char[] s) {
        StringBuilder res = new StringBuilder();
        int n = s.length;
        while (i < n && s[i] != ']') {
            if (s[i] < '0' || s[i] > '9') {
                res.append(s[i++]);
            } else {
                int cnt = 0;
                while (s[i] >= '0' && s[i] <= '9') {
                    cnt = cnt * 10 + s[i++] - '0';
                }
                ++i;
                String t = decode(s);
                ++i;
                while (cnt-- > 0) {
                    res.append(t);
                }
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        Leet394_DecodeString ins = new Leet394_DecodeString();

        String input = "abc3[cd]xyz";
        System.out.println(ins.decodeString(input));
    }
}
