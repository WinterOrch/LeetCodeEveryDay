import java.util.List;

public class Leet120_Triangle {
    public int minimumTotal(List<List<Integer>> triangle) {
        int depth = triangle.size();
        int[] dp = new int[depth];

        dp[0] = triangle.get(0).get(0);

        for(int i = 1; i < depth; ++i) {
            dp[i] = dp[i - 1] + triangle.get(i).get(i);

            for(int j = i - 1; j > 0; --j) {
                dp[j] = Math.min(dp[j], dp[j - 1]) + triangle.get(i).get(j);
            }

            dp[0] += triangle.get(i).get(0);
        }

        int min = Integer.MAX_VALUE;
        for(int i = 0; i < depth; i++) {
            if(dp[i] < min)
                min = dp[i];
        }

        return min;
    }
}
