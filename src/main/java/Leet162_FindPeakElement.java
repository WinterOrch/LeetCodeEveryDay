public class Leet162_FindPeakElement {
    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;

        while(left < right) {
            int mid = left + (right - left) / 2;

            if(nums[mid] < nums[mid + 1])
                left = mid + 1;
            else
                right = mid;
        }

        return right;
    }

    public double sqrt(double x) {
        double l = 0;
        double r = Math.max(x, 1.0);
        double eps = 1e-12;

        while(l + eps < r) {
            double mid = l + (r - l) / 2;
            if(mid * mid < x)
                l = mid;
            else
                r = mid;
        }

        return l;
    }
}
