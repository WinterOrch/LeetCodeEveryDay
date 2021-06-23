package jianzhi;

import java.util.*;

public class _38 {
    int len;
    char[] chars;

    public String[] permutation(String s) {
        this.chars = s.toCharArray();
        this.len = chars.length;
        List<String> res = new ArrayList<>();

        permutation(0, res);

        return res.toArray(new String[0]);
    }

    private void permutation(int cur, List<String> res) {
        if (cur == this.len - 1) {
            res.add(String.copyValueOf(this.chars));
        } else {
            HashSet<Character> set = new HashSet<>();

            for (int i = cur; i < this.len; ++i) {
                if (set.contains(this.chars[i]))
                    continue;
                else
                    set.add(this.chars[i]);

                // Switch str[i] and str[cur]
                char temp = this.chars[i];
                this.chars[i] = this.chars[cur];
                this.chars[cur] = temp;

                permutation(cur + 1, res);

                // Switch them back
                temp = this.chars[i];
                this.chars[i] = this.chars[cur];
                this.chars[cur] = temp;
            }
        }
    }

    public static void main(String[] args) {
        _38 ins = new _38();

        System.out.println(Arrays.toString(ins.permutation("abc")));
    }
}
