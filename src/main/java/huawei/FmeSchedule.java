package huawei;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class FmeSchedule {

    private static int getMaxCredit(int[][] sheets) {
        Arrays.sort(sheets, ((o1, o2) -> o2[0] - o1[0]));

        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);

        int currentTick = sheets[0][0], res = 0;
        for (int[] sheet : sheets) {
            if (sheet[0] != currentTick) {
                int hours = currentTick - sheet[0];
                currentTick = sheet[0];

                while (hours > 0) {
                    if (pq.isEmpty()) {
                        break;
                    } else {
                        res += pq.poll();
                        --hours;
                    }
                }
            }
            pq.add(sheet[1]);
        }

        return res;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int numSheets = in.nextInt();
        int[][] sheets = new int[numSheets + 1][2];

        for (int i = 0; i < numSheets; ++i) {
            sheets[i][0] = in.nextInt();
            sheets[i][1] = in.nextInt();
        }
        sheets[numSheets][0] = 0;
        sheets[numSheets][1] = 0;

        System.out.println(getMaxCredit(sheets));
    }
}
