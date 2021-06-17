public class Leet60_PermutationSequence {
    /**
     * 一位一位确定，边加边删
     * created in 10:35 2021/4/7
     */
    public String getPermutation(int n, int k) {
        StringBuilder numbers = new StringBuilder("123456789");
        StringBuilder output = new StringBuilder();

        int[] perm = new int[n];

        perm[0] = 1;
        for(int i = 1; i < n; ++i) {
            perm[i] = perm[i - 1] * i;
        }

        k -= 1;

        for(int i = n; i >= 1; --i) {
            int a = k / perm[i - 1];
            k = k % perm[i - 1];

            output.append(numbers.charAt(a));
            numbers.deleteCharAt(a);
        }

        return output.toString();
    }
}
