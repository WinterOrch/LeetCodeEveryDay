import java.util.Arrays;

public class Leet1723_FindMinimumTimeToFinishAllJobs {
    /**
     * 二分法
     * created in 14:44 2021/5/8
     */
    public int minimumTimeRequired(int[] jobs, int k) {
        Arrays.sort(jobs);

        int sum = 0;

        int i = 0, j = jobs.length - 1;
        while (i < j) {
            sum += jobs[i] + jobs[j];

            int tmp = jobs[i];
            jobs[i++] = jobs[j];
            jobs[j--] = tmp;
        }
        if (i == j)
            sum += jobs[i];

        int left = jobs[0], right = sum;
        while (left < right) {
            int mid = left + (right - left) / 2;

            if (check(jobs, k, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean check(int[] jobs, int k, int lim) {
        int[] loads = new int[k];
        return canBeSplit(jobs, loads, 0, lim);
    }

    private boolean canBeSplit(int[] jobs, int[] loads, int i, int lim) {
        if (i >= jobs.length)
            return true;

        // 反复向抽屉中装球
        int cur = jobs[i];
        for (int j = 0; j < loads.length; ++j) {
            if (loads[j] + cur <= lim) {
                loads[j] += cur;
                if (canBeSplit(jobs, loads, i + 1, lim)) {
                    return true;
                }
                loads[j] -= cur;
            }

            // 如果当前抽屉只装cur也不可行，则接下来只能装比cur小的，同样不会可行，剪掉
            // 如果当前抽屉（装了cur后）已经分配满上限却不可行，那接下来也不会有其它装法，剪掉
            if (loads[j] == 0 || loads[j] + cur == lim) {
                break;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Leet1723_FindMinimumTimeToFinishAllJobs ins = new Leet1723_FindMinimumTimeToFinishAllJobs();

        int[] jobs = {1,2,4,7,8};
        System.out.println(ins.minimumTimeRequired(jobs, 2));
    }
}
