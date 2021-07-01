import java.util.Arrays;

public class Leet3_LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        int[] set = new int[256];
        Arrays.fill(set, -1);

        int res = 0, left = -1;
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; ++i) {
            left = Math.max(left, set[arr[i]]);
            set[arr[i]] = -1;
            res = Math.max(res, i - left);
        }

        return res;
    }
}
