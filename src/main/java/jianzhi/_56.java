package jianzhi;

public class _56 {
    public int[] singleNumbers(int[] nums) {
        int x = 0, y = 0, n = 0, m = 1;
        for(int num : nums)               // 1. 遍历异或
            n ^= num;
        while((n & m) == 0)               // 2. 循环左移，计算 m
            m <<= 1;
        for(int num: nums) {              // 3. 遍历 nums 分组
            if((num & m) != 0)
                x ^= num;                 // 4. m 位上为 0 的
            else
                y ^= num;                 // 4. m 位上为 0 的数
        }
        return new int[] {x, y};          // 5. 返回出现一次的数字
    }
}
