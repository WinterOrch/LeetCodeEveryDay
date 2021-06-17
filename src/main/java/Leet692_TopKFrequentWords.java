import java.util.*;

@SuppressWarnings("Duplicates")
public class Leet692_TopKFrequentWords {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> cnt = new HashMap<>();

        for(String str : words) {
            if(cnt.containsKey(str)) {
                cnt.compute(str, (key, value) -> {
                    value += 1;
                    return value;
                });
            }else {
                cnt.put(str, 1);
            }
        }

        List<String> rec = new ArrayList<>(cnt.keySet());
        rec.sort((word1, word2) -> cnt.get(word1).equals(cnt.get(word2)) ?
                word1.compareTo(word2) : cnt.get(word2) - cnt.get(word1));
        return rec.subList(0, k);
    }

    public List<String> topKFrequent_2(String[] words, int k) {
        List<String> res = new ArrayList<>();

        Map<String, Integer> cnt = new HashMap<>();
        for(String str : words) {
            if(cnt.containsKey(str)) {
                cnt.compute(str, (key, value) -> {
                    value += 1;
                    return value;
                });
            }else {
                cnt.put(str, 1);
            }
        }

        PriorityQueue<String> pq = new PriorityQueue<>((word1, word2) -> cnt.get(word1).equals(cnt.get(word2)) ?
                word2.compareTo(word1) : cnt.get(word1) - cnt.get(word2));

        for (String key : cnt.keySet()) {
            pq.offer(key);
            if (pq.size() > k)
                pq.poll();
        }

        while (!pq.isEmpty()) {
            res.add(pq.poll());
        }
        Collections.reverse(res);

        return res;
    }
}
