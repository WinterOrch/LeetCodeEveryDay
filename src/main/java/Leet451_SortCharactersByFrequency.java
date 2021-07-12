import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("Duplicates")
public class Leet451_SortCharactersByFrequency {
    public String frequencySort(String s) {
        if(s.length() < 3)
            return s;

        StringBuilder res = new StringBuilder();

        char[] arr = s.toCharArray();

        int[] map = new int[256];

        for (char c : arr) {
            map[c]++;
        }

        int maxOccurrence = 1, len = 0;
        int[] reference = new int[arr.length + 1];
        for (int occur : map) {
            if (occur > 0) {
                reference[occur]++;
                len++;
                if (occur > maxOccurrence) {
                    maxOccurrence = occur;
                }
            }
        }

        for (int i = 1; i <= maxOccurrence; ++i) {
            reference[i] += reference[i - 1];
        }

        char[] dict = new char[len];
        for (char i = 0; i < 256; ++i) {
            if (map[i] > 0) {
                dict[len - 1 - --reference[map[i]]] = i;
            }
        }

        for (char tmp : dict) {
            for (int i = 0; i < map[tmp]; ++i) {
                res.append(tmp);
            }
        }

        return res.toString();



//        Map<Character, Integer> map = new HashMap<>();
//        for(char c : arr) {
//            if(map.containsKey(c)) {
//                map.compute(c, (key, v) -> {
//                    ++v;
//                    return v;
//                });
//            }else {
//                map.put(c, 1);
//            }
//        }
//
//        Set<Map.Entry<Character, Integer>> entrySet = map.entrySet();
//
//        int maxOccurrence = 1;
//        int[] reference = new int[arr.length + 1];
//        for(Map.Entry<Character, Integer> entry : entrySet) {
//            reference[entry.getValue()]++;
//            if(maxOccurrence < entry.getValue())
//                maxOccurrence = entry.getValue();
//        }
//
//        for(int i = 1; i <= maxOccurrence; ++i) {
//            reference[i] += reference[i - 1];
//        }
//
//        int num = entrySet.size();
//        char[] dict = new char[num];
//        for(Map.Entry<Character, Integer> entry : entrySet) {
//            reference[entry.getValue()]--;
//            dict[num - 1 - reference[entry.getValue()]] = entry.getKey();
//        }
//
//        for(Character tmp : dict) {
//            for(int i = 0; i < map.get(tmp); ++i)
//                res.append(tmp);
//        }
//
//        return res.toString();
    }

    public static void main(String[] args) {
        Leet451_SortCharactersByFrequency ins = new Leet451_SortCharactersByFrequency();

        String input = "tree";

        System.out.print(ins.frequencySort(input));
    }
}
