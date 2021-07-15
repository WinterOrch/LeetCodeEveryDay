public class Leet42_TrappingRainWater {
    public int trap(int[] height) {
        int[] stack = new int[height.length];
        int top = -1;

        int res = 0, i = 0;
        while (i < height.length) {
            if (top < 0 || height[stack[top]] >= height[i]) {
                stack[++top] = i++;
            } else {
                int button = stack[top--];
                if (top >= 0) {
                    res += (Math.min(height[i], height[stack[top]]) - height[button]) * (i - stack[top] - 1);
                } else {
                    stack[++top] = i++;
                }
            }
        }
        return res;
    }

    public int dp(int[] height) {
        int res = 0, mx = 0, n = height.length;
        int[] dp = new int[n];
        for (int i = 0; i < n; ++i) {
            dp[i] = mx;
            mx = Math.max(mx, height[i]);
        }
        mx = 0;
        for (int i = n - 1; i >= 0; --i) {
            dp[i] = Math.min(dp[i], mx);
            mx = Math.max(mx, height[i]);
            if (dp[i] - height[i] > 0) res += dp[i] - height[i];
        }
        return res;
    }

    public static void main(String[] args) {
        Leet42_TrappingRainWater ins = new Leet42_TrappingRainWater();
        System.out.println(ins.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }
}
