public class Leet1052_FrumpyBookstoreOwner {
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int res = 0, n = customers.length, max = 0;
        int[] sums = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            sums[i + 1] = sums[i];
            if (grumpy[i] == 0) {
                res += customers[i];
            } else {
                sums[i + 1] += customers[i];
            }

            if (i + 1 - minutes >= 0) {
                max = Math.max(max, sums[i + 1] - sums[i + 1 - minutes]);
            }
        }
        return res + max;
    }
}
