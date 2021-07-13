import java.util.Arrays;

public class Leet16_3SumClosest {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);

        int res = nums[0] + nums[1] + nums[2], dis = Math.abs(res - target);
        for (int i = 0; i < nums.length; ++i) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue;

            int t = target - nums[i];
            int l = i + 1, r = nums.length - 1;
            while (l < r) {
                int temp = nums[l] + nums[r];
                if (temp == t) {
                    return target;
                } else if (temp < t) {
                    if (t - temp < dis) {
                        dis = t - temp;
                        res = temp + nums[i];
                    }
                    ++l;
                } else {
                    if (temp - t < dis) {
                        dis = temp - t;
                        res = temp +nums[i];
                    }
                    --r;
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        Leet16_3SumClosest ins = new Leet16_3SumClosest();
        System.out.println(ins.threeSumClosest(new int[]{-1, 2, 1, -4}, 1));
    }
}
