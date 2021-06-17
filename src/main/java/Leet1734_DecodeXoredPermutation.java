public class Leet1734_DecodeXoredPermutation {
    public int[] decode(int[] encoded) {
        int n = encoded.length + 1;

        int tot = 0;
        for (int i = 1; i <= n; ++i) {
            tot ^= i;
        }

        int odd = 0;
        for (int i = 1; i < n - 1; i += 2) {
            odd ^= encoded[i];
        }

        int[] perm = new int[n];
        perm[0] = tot ^ odd;
        for (int i = 0; i < n - 1; ++i) {
            perm[i + 1] = perm[i] ^ encoded[i];
        }

        return perm;
    }
}
