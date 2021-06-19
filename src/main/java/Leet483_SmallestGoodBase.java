public class Leet483_SmallestGoodBase {
    public String smallestGoodBase(String n) {
        long nL = Long.parseLong(n);

        for (int i = (int) (Math.log(nL + 1) / Math.log(2)); i >= 2; --i) {
            long left = 2, right = (long) Math.pow(nL, 1.0 / (i - 1)) + 1;
            while (left < right) {
                long mid = left + (right - left) / 2, sum = 0;
                for (int j = 0; j < i; ++j) {
                    sum = sum * mid + 1;
                }
                if (sum == nL) {
                    return String.valueOf(mid);
                }
                if (sum < nL) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
        }

        return String.valueOf(nL - 1);
    }
}
