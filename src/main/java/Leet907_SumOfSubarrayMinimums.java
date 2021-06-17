import java.util.ArrayDeque;
import java.util.Deque;

public class Leet907_SumOfSubarrayMinimums {
    static final int MOD = 1_000_000_007;

    /**
     * 1.用 int LC 中会有最后一个案例溢出，且 Deque 需要拆装箱，性能不尽如人意
     * created in 17:55 2021/5/27
     */
    public int sumSubarrayMins(int[] arr) {
        int res = 0, n = arr.length;
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i <= n; ++i) {
            int cur = (i == n) ? 0 : arr[i];
            while (!stack.isEmpty() && cur < arr[stack.peekLast()]) {
                int idx = stack.pollLast();
                //  Left 为以当前数为最小值时，左侧最多能延伸几个 index (在碰到比自己小的数之前)
                int left = idx - (stack.isEmpty() ? -1 : stack.peekLast());
                //  Right 为以当前数为最小值时，右侧最多能延伸几个 index (在碰到比自己小的数之前)
                int right = i - idx;
                res = (res + (arr[idx] * left * right) % MOD) % MOD;
            }
            stack.addLast(i);
        }

        return res;
    }

    /**
     * 2.手工 long[] 作为单调栈，性能好一些
     * created in 17:56 2021/5/27
     */
    static final long MOD_LONG = 1_000_000_007;
    public int sumSubarrayMins_long(int[] arr) {
        long res = 0, n = arr.length;
        long[] stack = new long[arr.length];
        int top = -1;

        for (long i = 0; i <= n; ++i) {
            long cur = (i == n) ? 0 : arr[(int) i];
            while (top > -1 && cur < arr[(int) stack[top]]) {
                long idx = stack[top--];
                //  Left 为以当前数为最小值时，左侧最多能延伸几个 index (在碰到比自己小的数之前)
                long left = idx - (top < 0 ? -1 : stack[top]);
                //  Right 为以当前数为最小值时，右侧最多能延伸几个 index (在碰到比自己小的数之前)
                long right = i - idx;
                res += (arr[(int) idx] * left * right) % MOD_LONG;
                res %= MOD_LONG;
            }
            stack[++top] = i;
        }

        return (int) res;
    }

    public static void main(String[] args) {
        Leet907_SumOfSubarrayMinimums ins = new Leet907_SumOfSubarrayMinimums();

        int[] arr = {3,1,2,4};

        System.out.println(ins.sumSubarrayMins(arr));
    }
}
