import java.util.Arrays;

public class Leet1482_MinimumNumberOfDaysToMakeMBouquets {
    public int minDays(int[] bloomDay, int m, int k) {
        if (bloomDay.length < m * k)
            return -1;

        int[] sorted = new int[bloomDay.length];
        System.arraycopy(bloomDay, 0, sorted, 0, bloomDay.length);
        Arrays.sort(sorted);

        int l = sorted[m * k - 1], r = sorted[sorted.length - 1];

        while (l < r) {
            int mid = l + (r - l) / 2;

            if (check(bloomDay, m, k, mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        return l;
    }

    private boolean check(int[] bloomDay, int m, int k, int day) {
        int collection = 0;
        for (int j : bloomDay) {
            if (j <= day) {
                if (++collection == k) {
                    m--;

                    if (m == 0) {
                        return true;
                    } else {
                        collection = 0;
                    }
                }
            } else {
                collection = 0;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] bloomDays = {7,7,7,7,12,7,7};
        int m = 2, k = 3;

        Leet1482_MinimumNumberOfDaysToMakeMBouquets ins = new Leet1482_MinimumNumberOfDaysToMakeMBouquets();
        System.out.println(ins.minDays(bloomDays, m, k));
    }
}
