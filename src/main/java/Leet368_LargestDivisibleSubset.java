import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Leet368_LargestDivisibleSubset {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> res = new ArrayList<>();

        Arrays.sort(nums);
        //  DP来解决“最长”子集的问题
        int[] dp = new int[nums.length];
        //  DP没法输出结果，所以通过一个索引链来存结果
        int[] end = new int[nums.length];

        int maxLen = 0;
        int maxIdx = 0;

        for(int i = nums.length - 1; i >= 0; --i) {
            for(int j = i; j < nums.length; ++j) {
                if(nums[j] % nums[i] == 0 && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                    end[i] = j;

                    if(maxLen < dp[i]) {
                        maxLen = dp[i];
                        maxIdx = i;
                    }
                }
            }
        }

        for(int i = 0; i < maxLen; ++i) {
            res.add(nums[maxIdx]);
            maxIdx = end[maxIdx];
        }

        return res;
    }

    @SuppressWarnings("DuplicatedCode")
    public List<Integer> largestDivisibleSubsetFromFront(int[] nums) {
        List<Integer> res = new ArrayList<>();

        Arrays.sort(nums);
        //  DP来解决“最长”子集的问题
        int[] dp = new int[nums.length];
        //  DP没法输出结果，所以通过一个索引链来存结果
        int[] end = new int[nums.length];

        int maxLen = 0;
        int maxIdx = 0;

        for(int i = 0; i < nums.length; ++i) {
            for(int j = i; j >= 0; --j) {
                if(nums[i] % nums[j] == 0 && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                    end[i] = j;

                    if(maxLen < dp[i]) {
                        maxLen = dp[i];
                        maxIdx = i;
                    }
                }
            }
        }

        for(int i = 0; i < maxLen; ++i) {
            res.add(nums[maxIdx]);
            maxIdx = end[maxIdx];
        }

        Collections.reverse(res);

        return res;
    }

    public static void main(String[] args) {
        Leet368_LargestDivisibleSubset ins = new Leet368_LargestDivisibleSubset();

        int[] input = {1, 2, 4, 8};

        System.out.println(ins.largestDivisibleSubset(input));
    }
}
