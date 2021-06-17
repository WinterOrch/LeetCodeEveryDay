public class Leet80_RemoveDuplicatesFromSortedArrayII {
    public int removeDuplicates(int[] nums) {
        int realIdx = 0;

        for(int num : nums) {
            if(realIdx < 2 || num > nums[realIdx - 2]) {
                nums[realIdx ++] = num;
            }
        }

        return realIdx;
    }
}
