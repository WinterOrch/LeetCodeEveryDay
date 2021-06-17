import java.util.*;

public class Leet1449_FormLargestIntegerWithDigitsThatAddUpToTarget {
    public String largestNumber(int[] cost, int target) {
        Set<Integer> ok = new HashSet<>();

        int[] dp = new int[target + 1];
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = 0;

        for (int c : cost) {
            if (ok.contains(c))
                continue;
            else
                ok.add(c);

            for (int j = c; j <= target; ++j) {
                if (dp[j - c] >= dp[j]) {
                    dp[j] = dp[j - c] + 1;
                }
            }
        }

        if (dp[target] < 0) {
            return "0";
        }

        StringBuilder res = new StringBuilder();
        for (int i = 8, j = target; i >= 0; i--) {
            for (int c = cost[i]; j >= c && dp[j] == dp[j - c] + 1; j -= c) {
                res.append(i + 1);
            }
        }
        return res.toString();
    }
}
