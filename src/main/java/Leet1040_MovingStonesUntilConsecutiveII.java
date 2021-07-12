import java.util.Arrays;

public class Leet1040_MovingStonesUntilConsecutiveII {
    public int[] numMovesStonesII(int[] stones) {
        Arrays.sort(stones);
        int n = stones.length, low = n, i = 0;
        for (int j = 0; j < n; ++j) {
            while (stones[j] - stones[i] + 1 > n)
                ++i;

            int al_store = j - i + 1;
            if (al_store == n - 1 && stones[j] - stones[i] + 1 == n - 1) {
                low = Math.min(low, 2);
            } else {
                low = Math.min(low, n - al_store);
            }
        }
        return new int[]{low, Math.max(stones[n - 1] - stones[1] - n + 2, stones[n - 2] - stones[0] - n + 2)};
    }
}
