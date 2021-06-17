public class Leet287_FindTheDuplicateNumber {
    public int findDuplicate(int[] nums) {
        int left = 1, right = nums.length;

        while(left < right) {
            int mid = left + (right - left) / 2;
            int cnt = 0;
            for(int num : nums) {
                if(num <= mid)
                    ++cnt;
            }

            if(cnt <= mid)
                left = mid + 1;
            else
                right = mid;
        }

        return right;
    }

    /**
     * 快慢指针
     * 这道题的数字范围正好满足要求，如果存在重复数字，可以形成环
     * 利用快慢指针找到环，然后用tmp找到环的起点
     * created in 12:00 2021/4/11
     */
    public int findDuplicate_1(int[] nums) {
        int sPt = 0, fPt = 0, tmp = 0;
        while(true) {
            sPt = nums[sPt];
            fPt = nums[nums[fPt]];
            if(sPt == fPt)
                break;
        }
        while(true) {
            sPt = nums[sPt];
            tmp = nums[tmp];
            if(sPt == tmp)
                break;
        }
        return sPt;
    }

    public static void main(String[] args) {
        Leet287_FindTheDuplicateNumber ins = new Leet287_FindTheDuplicateNumber();

        int[] arr = {1, 3, 4, 2, 2};
        System.out.println(ins.findDuplicate_1(arr));
    }
}
