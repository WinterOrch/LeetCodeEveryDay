import java.util.ArrayList;
import java.util.List;

public class Leet401_BinaryWatch {
//    private static final int[][] hours = {{0},{1,2,4,8},{3,5,9,6,10},{7,11}};
//    private static final int[][] minutes = {{0},{1,2,4,8,16,32},{3,5,9,17,33,6,10,18,34,12,20,36,24,40,48},{7,11,19,35,13,21,37,25,41,49,14,22,38,26,42,50,28,44,52,56},{15,23,39,27,43,51,29,45,53,57,30,46,54,58},{31,47,55,59}};
//
//    public List<String> readBinaryWatch(int turnedOn) {
//        List<String> res = new ArrayList<>();
//        for (int k = 0; k <= turnedOn; ++k) {
//            int t = turnedOn - k;
//            if (k > 3 || t > 5)
//                continue;
//
//            for (int hour : hours[k]) {
//                for (int minute : minutes[t]) {
//                    res.add(hour + ":" + ((minute < 10 ? "0" : "") + minute));
//                }
//            }
//        }
//        return res;
//    }

    // 回溯
    int[] hours = new int[] {1, 2, 4, 8, 0, 0, 0, 0, 0, 0};
    int[] minutes = new int[] {0, 0, 0, 0, 1, 2, 4, 8, 16, 32};
    List<String> res = new ArrayList<>();

    public List<String> readBinaryWatch(int num) {
        backtrack(num, 0, 0, 0);
        return res;
    }

    public void backtrack(int num, int index, int hour, int minute){
        if (hour > 11 || minute > 59)
            return;

        if (num == 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(hour).append(':');
            if (minute < 10) {
                sb.append('0');
            }
            sb.append(minute);
            res.add(sb.toString());
            return;
        }

        for (int i = index; i < 10; i++) {
            backtrack(num - 1, i + 1, hour + hours[i], minute + minutes[i]);
        }
    }
}
