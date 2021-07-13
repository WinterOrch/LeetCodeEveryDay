import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leet15_3Sum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        if (nums.length < 3) {
            return res;
        }

        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; ++i) {
            if (nums[i] > 0)
                break;

            if (i > 0 && nums[i] == nums[i - 1])
                continue;

            int l = i + 1, r = nums.length - 1;
            int target = -nums[i];
            while (l < r) {
                if (nums[l] + nums[r] == target) {
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));

                    ++l;
                    while (l < r && nums[l] == nums[l - 1]) {
                        ++l;
                    }
                    --r;
                    while (l < r && nums[r] == nums[r + 1]) {
                        --r;
                    }
                } else if (nums[l] + nums[r] < target) {
                    ++l;
                } else {
                    --r;
                }
            }
        }

        return res;
    }
}
