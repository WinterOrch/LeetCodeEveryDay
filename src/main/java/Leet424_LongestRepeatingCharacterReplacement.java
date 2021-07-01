public class Leet424_LongestRepeatingCharacterReplacement {
    public int characterReplacement(String s, int k) {
        int[] set = new int[26];

        int res = 0, maxCnt = 0;

        int left = 0;
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; ++i) {
            // 值得注意的是，随着右指针新取到一个字符c，窗口中出现最多的字符
            // 要么不变，要么变成新字符
            maxCnt = Math.max(maxCnt, ++set[arr[i] - 'A']);
            while (i - left + 1 - maxCnt > k) {
                --set[arr[left++] - 'A'];
            }
            res = Math.max(res, i - left + 1);
        }

        return res;
    }
}
