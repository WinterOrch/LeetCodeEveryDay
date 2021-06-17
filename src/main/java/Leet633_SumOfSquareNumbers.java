public class Leet633_SumOfSquareNumbers {
    public boolean judgeSquareSum(int c) {
        int left = 0, right = (int) Math.sqrt(c);

        while(left <= right) {
            int tmp = left * left + right * right;

            if(tmp == c)
                return true;
            if(tmp < c)
                left++;
            else
                right--;
        }

        return false;
    }

    /**
     * Based on Fermat's theorem on sums of two squares
     * 费马平方和定理：
     * 当某个数字的 4k+3 型的质数因子的个数均为偶数时，其可以拆分为两个平方数之和
     * created in 11:06 2021/4/28
     */
    public boolean judgeSquareSum2(int c) {
        for (int i = 2; i * i <= c; ++i) {
            if (c % i != 0)
                continue;
            int cnt = 0;
            while (c % i == 0) {
                ++cnt;
                c /= i;
            }
            if (i % 4 == 3 && cnt % 2 != 0)
                return false;
        }
        return c % 4 != 3;
    }
}
