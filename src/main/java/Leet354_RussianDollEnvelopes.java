import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leet354_RussianDollEnvelopes {
    public int maxEnvelopes(int[][] envelopes) {
        //Arrays.sort(envelopes, Comparator.comparingInt((int[] array) -> array[0])
        //                                 .thenComparing(array -> -array[1]));

        Arrays.sort(envelopes, (e1, e2) -> {
            if (e1[0] != e2[0]) {
                return e1[0] - e2[0];
            } else {
                // 高度逆序排在DP过程中会快一些
                return e2[1] - e1[1];
            }
        });

        // 由于主序是宽度，只要关注高度就可以了
        List<Integer> dp = new ArrayList<>();
        for (int[] envelope : envelopes) {
            int left = 0, right = dp.size(), t = envelope[1];
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (dp.get(mid) < t)
                    left = mid + 1;
                else
                    right = mid;
            }

            if (right >= dp.size())
                dp.add(t);
            else
                dp.set(right, t);
        }

        return dp.size();
    }

    public static void main(String[] args) {
        int[][] env = {{2,100},{3,200},{4,300},{5,500},{5,400},{5,250},{6,370},{6,360},{7,380}};
        Leet354_RussianDollEnvelopes ins = new Leet354_RussianDollEnvelopes();

        System.out.println(ins.maxEnvelopes(env));
    }
}
