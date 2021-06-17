import java.util.HashSet;
import java.util.Set;

public class Leet523_ContinuousSubarraySum {
    public boolean checkSubarraySum(int[] nums, int k) {
        int sum = 0, pre = 0;

        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            sum += num;
            int t = sum % k;
            if (set.contains(t))
                return true;

            set.add(pre);
            pre = t;
        }

        return false;
    }
}
