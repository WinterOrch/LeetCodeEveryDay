import java.util.Arrays;

public class Leet179_LargestNumber {
    /**
     * 用String类compareTo的解法
     * 相较用Integer[]的要慢一些
     * created in 10:22 2021/4/12
     */
    public String largestNumber(int[] nums) {
        String[] strNums = new String[nums.length];

        for(int i = 0; i < nums.length; ++i) {
            strNums[i] = Integer.toString(nums[i]);
        }
        Arrays.sort(strNums, (String n1, String n2) -> (n2 + n1).compareTo(n1 + n2));

        if(strNums[0].equals("0"))
            return "0";

        StringBuilder res = new StringBuilder();
        for(String s : strNums)
            res.append(s);

        return res.toString();
    }

    /**
     * 改用Integer[]比较起来快一些
     * created in 10:25 2021/4/12
     */
    public String largestNumber_2(int[] nums) {
        int n = nums.length;
        Integer[] numsArr = new Integer[n];
        for (int i = 0; i < n; i++) {
            numsArr[i] = nums[i];
        }

        Arrays.sort(numsArr, (x, y) -> {
            long sx = 10, sy = 10;
            while (sx <= x) {
                sx *= 10;
            }
            while (sy <= y) {
                sy *= 10;
            }
            return (int) (-sy * x - y + sx * y + x);
        });

        if(numsArr[0] == 0)
            return "0";

        StringBuilder res = new StringBuilder();
        for(int s : numsArr)
            res.append(s);

        return res.toString();
    }

    public static void  main(String[] args) {
        int[] input = {0,0};

        Leet179_LargestNumber ins = new Leet179_LargestNumber();

        System.out.println(ins.largestNumber(input));
    }
}
