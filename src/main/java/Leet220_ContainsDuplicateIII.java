import java.util.TreeMap;
import java.util.TreeSet;

public class Leet220_ContainsDuplicateIII {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if(k < 0 || t < 0)
            return false;

        TreeMap<Long, Integer> map = new TreeMap<>();
        int j = 0;

        for(int i = 0; i < nums.length; ++i) {
            if(i - j > k)
                map.remove((long)nums[j++]);

            Long tmpKey = map.ceilingKey((long)(nums[i] - t));
            if(null != tmpKey && Math.abs(tmpKey - nums[i]) <= t)
                return true;

            if(map.size() > 0 && null == tmpKey)
                tmpKey = map.floorKey((long)(nums[i] + t));
            if(null != tmpKey && Math.abs(tmpKey - nums[i]) <= t)
                return true;


            map.put((long)nums[i], i);
        }

        return false;
    }

    private long getID(long x, long w) {
        return x < 0 ? (x + 1) / w - 1 : x / w;
    }

    public boolean contains(int[] nums, int k, int t) {
        TreeSet<Long> set = new TreeSet<>();
        for(int i = 0; i < nums.length; ++i) {
            Long s = set.ceiling((long)nums[i]);
            if(null != s && nums[i] + t >= s)
                return true;

            Long g = set.floor((long)nums[i]);
            if(g != null && nums[i] <= g + t)
                return true;

            set.add((long)nums[i]);
            if(set.size() > k)
                set.remove((long)nums[i - k]);
        }

        return false;
    }

    public static void main(String[] args) {
        int[] tmp = {-2147483640,-2147483641};

        Leet220_ContainsDuplicateIII ins = new Leet220_ContainsDuplicateIII();

        System.out.println(ins.containsNearbyAlmostDuplicate(tmp, 1, 100));
    }
}
