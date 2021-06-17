import java.util.Arrays;
import java.util.Comparator;

public class Leet274_HIndex {
    /**
     * 要算H因子直接按引用数量降序排列就行了
     * 不过用Java写就有点捉鸡
     * created in 14:30 2021/4/17
     */
    public int hIndex(int[] citations) {
        Integer[] c = new Integer[citations.length];
        for(int i = 0; i < citations.length; ++i)
            c[i] = citations[i];

        Arrays.sort(c, Comparator.comparingInt((Integer o) -> -o));

        for(int i = 0; i < citations.length; ++i) {
            if(i >= c[i])
                return i;
        }

        return citations.length;
    }

    public int hIndex_2(int[] citations) {
        int n = citations.length;
        int[] papers = new int[n + 1];

        for(int c : citations)
            ++papers[Math.min(n, c)];

        int k = n;
        for(int s = papers[n]; k > s; s += papers[k])
            k--;

        return k;
    }

    public static void main(String[] args) {
        int[] arr = {3, 0, 6, 1, 5};

        Leet274_HIndex ins = new Leet274_HIndex();
        System.out.println(ins.hIndex(arr));
    }
}
