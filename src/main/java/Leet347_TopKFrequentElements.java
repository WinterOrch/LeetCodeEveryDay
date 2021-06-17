import java.util.*;

@SuppressWarnings("Duplicates")
public class Leet347_TopKFrequentElements {
    /**
     * 既然是前K大，肯定是能用堆的
     * created in 16:07 2021/4/17
     */
    public int[] topKFrequent(int[] nums, int k) {
        int[] res = new int[k];

        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums) {
            if(map.containsKey(num)) {
                map.compute(num, (key, v) -> {
                    ++v;
                    return v;
                });
            }else {
                map.put(num, 1);
            }
        }

        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap =
                new PriorityQueue<>(Comparator.comparingInt(o -> -o.getValue()));
        maxHeap.addAll(map.entrySet());

        for(int i = 0; i < k; ++i)
            res[i] = maxHeap.poll().getKey();

        return res;
    }

    /**
     * 用计数排序试一试
     * created in 16:07 2021/4/17
     */
    public int[] topKFrequent2(int[] nums, int k) {
        int[] res = new int[k];
        int resIdx = 0;

        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums) {
            if(map.containsKey(num)) {
                map.compute(num, (key, v) -> {
                    ++v;
                    return v;
                });
            }else {
                map.put(num, 1);
            }
        }

        Set<Map.Entry<Integer, Integer>> entrySet = map.entrySet();

        int maxOccurrence = 1;
        int[] reference = new int[nums.length + 1];
        for(Map.Entry<Integer, Integer> entry : entrySet) {
            reference[entry.getValue()]++;
            if(maxOccurrence < entry.getValue())
                maxOccurrence = entry.getValue();
        }

        for(int i = 1; i <= maxOccurrence; ++i) {
            reference[i] += reference[i - 1];
        }

        k = entrySet.size() - k - 1;
        for(Map.Entry<Integer, Integer> entry : entrySet) {
            reference[entry.getValue()]--;
            if(reference[entry.getValue()] > k)
                res[resIdx++] = entry.getKey();
        }

        return res;
    }

    public static void main(String[] args) {
        Leet347_TopKFrequentElements ins = new Leet347_TopKFrequentElements();

        int[] input = {1,1,1,2,2,3};
        int k = 2;

        int[] res = ins.topKFrequent(input, k);

        for(int r : res) {
            System.out.print(r + " ");
        }
    }
}
