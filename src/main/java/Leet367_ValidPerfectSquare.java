public class Leet367_ValidPerfectSquare {
    /**
     * 虽然并不难，但是这题雷挺多的
     * created in 10:35 2021/4/9
     */
    public boolean isPerfectSquare(int num) {
        if(1 == num)    return true;
        int tmp = num / 2;
        int tmpRes;

        while((num / tmp) < tmp) {
            tmp /= 2;
        }

        int left = tmp;
        int right = 2 * tmp;

        while(left <= right) {
            int mid = left + (right - left) / 2;

            tmpRes = num / mid;
            if(tmpRes == mid) {
                if(num == mid * mid)
                    return true;
                else if(num < mid * mid)
                    right = mid - 1;
                else
                    left = mid + 1;
                continue;
            }

            if(tmpRes < mid) {
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Leet367_ValidPerfectSquare ins = new Leet367_ValidPerfectSquare();
        System.out.println(ins.isPerfectSquare(5));
    }
}
