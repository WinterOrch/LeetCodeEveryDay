import java.util.PriorityQueue;

public class Leet264_UglyNumberII {
    public int nthUglyNumber(int n) {
        int[] res = new int[n];
        res[0] = 1;

        int i2 = 0, i3 = 0, i5 = 0;
        for(int i = 1; i < n; ++i) {
            int m2 = res[i2] * 2;
            int m3 = res[i3] * 3;
            int m5 = res[i5] * 5;

            int mn = Math.min(m2, Math.min(m3, m5));

            if(m2 == mn) {
                ++i2;
            }
            if(m3 == mn) {
                ++i3;
            }
            if(m5 == mn) {
                ++i5;
            }

            res[i] = mn;
        }

        return res[n - 1];
    }

    /**
     * 虽然可以用堆，但需要用Long，相当慢
     * created in 11:21 2021/4/11
     */
    public int nthUglyNumberWithHeap(int n) {
        PriorityQueue<Long> maxHeap = new PriorityQueue<>();
        maxHeap.add((long)1);

        for(int i = 1; i < n; ++i) {
            Long tmp = maxHeap.poll();
            while(!maxHeap.isEmpty() && maxHeap.peek().equals(tmp)) {
                maxHeap.poll();
            }
            maxHeap.add(tmp * 2);
            maxHeap.add(tmp * 3);
            maxHeap.add(tmp * 5);
        }

        int res = Math.toIntExact(maxHeap.peek());

        return res;
    }

    public static void main(String[] args) {
        Leet264_UglyNumberII ins = new Leet264_UglyNumberII();

        System.out.println(ins.nthUglyNumber(10));
    }
}
