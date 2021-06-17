public class Leet81_SearchInRotatedSortedArrayII {
    public boolean search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while(left <= right) {
            int mid = left + (right - left) / 2;

            if(nums[mid] == target)
                return true;

            if(nums[mid] < nums[right]) {
                if(target <= nums[right] && nums[mid] < target) {
                    //  要移的是left，所以上面right可以取等号
                    left = mid + 1;
                }else {
                    right = mid - 1;
                }
            }else if(nums[mid] > nums[right]) {
                if(target < nums[mid] && nums[left] <= target) {
                    //  要移的是right，所以上面left可以取等号
                    right = mid - 1;
                }else {
                    left = mid + 1;
                }
            }else
                right--;
        }

        return false;
    }
}
