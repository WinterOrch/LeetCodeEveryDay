import java.util.Arrays;

public class Leet135_Candy {
    /**
     * 两遍遍历的方法，还有个时间复杂度更低的一遍方法
     * created in 16:36 2021/5/11
     */
    public int candy(int[] ratings) {
        int n = ratings.length;

        int[] candies = new int[n];
        Arrays.fill(candies, 1);

        for (int i = 1; i < n; ++i) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }

        int res = candies[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            if (ratings[i] > ratings[i + 1] && candies[i] <= candies[i + 1]) {
                candies[i] = candies[i + 1] + 1;
            }
            res += candies[i];
        }

        return res;
    }

    public static void main(String[] args) {
        Leet135_Candy ins = new Leet135_Candy();
        int[] input = {1, 3, 4, 5, 2};

        System.out.println(ins.candy(input));
    }
}
