package sample;

public class Sqrt {

    /**
     * Leet69 Sqrt with Binary Search
     * created in 19:28 2021/4/10
     */
    public int mySqrt(int x) {
        if(x < 2)
            return x;

        int tmp = x / 2;

        while(x / tmp < tmp) {
            tmp /= 2;
        }

        int l = tmp;
        int r = 2 * tmp;

        while(l <= r) {
            int mid = l + (r - l) / 2;

            tmp = x / mid;

            if(mid == tmp)
                return mid;

            if(tmp < mid)
                r = mid - 1;
            else
                l = mid + 1;
        }

        return r;
    }

    public Double mySqrt(double x) {
        double l = 0;
        double r = Math.max(x, 1.0);
        //  以1e-12为精度限制
        double eps = 1e-12;

        while(l + eps < r) {
            double mid = l + (r - l) / 2;

            if(mid * mid < x) {
                l = mid;
            }else {
                r = mid;
            }
        }

        return l;
    }
}
