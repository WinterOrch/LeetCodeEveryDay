public class Leet154_FindMinimumInRotatedSortedArrayII {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while(left < right) {
            int mid = left + (right - left) / 2;

            if(nums[mid] > nums[right])
                left = mid + 1;
            else if(nums[mid] < nums[right])
                right = mid;
            else
                right--;
        }

        return nums[right];
    }

    public int findMinByDivideConquer(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }
    private int helper(int[] nums, int start, int end) {
        if(start == end)
            return nums[start];
        if(nums[start] < nums[end])
            return nums[start];
        int mid = (start + end) / 2;

        return  Math.min(helper(nums, start, mid), helper(nums, mid + 1, end));
    }
}
