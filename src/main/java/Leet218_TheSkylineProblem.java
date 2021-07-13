import java.util.*;

public class Leet218_TheSkylineProblem {
    static class ListNode {
        int value;
        ListNode next;

        ListNode(int value) {
            this.value = value;
        }
    }

    static class SortedList {
        ListNode head;

        int peekFirst() {
            if (head == null) {
                return 0;
            } else {
                return head.value;
            }
        }

        boolean insert(int value) {
            ListNode newNode = new ListNode(value);

            if (head == null) {
                head = newNode;
                return true;
            } else if (value > head.value) {
                newNode.next = head;
                head = newNode;
                return true;
            } else {
                ListNode cur = head, next = head.next;

                while (next != null && value < next.value) {
                    cur = next;
                    next = next.next;
                }

                cur.next = newNode;
                newNode.next = next;

                return false;
            }
        }

        boolean delete(int value) {
            if (head.value == value) {
                head = head.next;
                return head == null || value != head.value;
            } else {
                ListNode pre = head, cur = head.next;
                while (cur != null && cur.value > value) {
                    pre = cur;
                    cur = cur.next;
                }
                if (cur != null && cur.value == value) {
                    pre.next = cur.next;
                }
                return false;
            }
        }
    }

    public List<List<Integer>> getSkyline(int[][] buildings) {
        int len = buildings.length;

        int[][] starts = new int[len][2];
        int[][] ends = new int[len][2];

        for (int i = 0; i < len; ++i) {
            starts[i][0] = buildings[i][0];
            ends[i][0] = buildings[i][1];

            starts[i][1] = ends[i][1] = buildings[i][2];
        }

        Arrays.sort(starts, Comparator.comparingInt(o -> o[0]));
        Arrays.sort(ends, Comparator.comparingInt(o -> o[0]));

        List<List<Integer>> res = new ArrayList<>();

        SortedList ceil = new SortedList();

        int iS = 0, iE = 0;
        while (iE < len || iS < len) {
            int x;

            boolean topChanges = false;
            if (iE == len || (iS < len && starts[iS][0] < ends[iE][0])) {
                x = starts[iS][0];
                while (iS < len && starts[iS][0] == x) {
                    topChanges |= ceil.insert(starts[iS++][1]);
                }
            } else if (iS == len || starts[iS][0] > ends[iE][0]) {
                x = ends[iE][0];
                while (iE < len && ends[iE][0] == x) {
                    topChanges |= ceil.delete(ends[iE++][1]);
                }
            } else {
                x = starts[iS][0];
                while (iS < len && starts[iS][0] == x) {
                    topChanges |= ceil.insert(starts[iS++][1]);
                }
                while (iE < len && ends[iE][0] == x) {
                    topChanges |= ceil.delete(ends[iE++][1]);
                }
            }

            if (topChanges) {
                res.add(Arrays.asList(x, ceil.peekFirst()));
            }

        }

        return res;
    }

    public List<List<Integer>> getSkyline2(int[][] buildings) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (b[1] - a[1]));
        int[] boundaries = new int[2 * buildings.length];

        int i = 0;
        for (int[] building : buildings) {
            boundaries[i++] = building[0];
            boundaries[i++] = building[1];
        }
        // 开始和结束都有可能成为线段的左端点
        Arrays.sort(boundaries);

        List<List<Integer>> res = new ArrayList<>();
        i = 0;
        for (int boundary : boundaries) {
            // 将开始点小于当前点的全部加进大顶堆
            while (i < buildings.length && buildings[i][0] <= boundary) {
                pq.offer(new int[]{buildings[i][1], buildings[i][2]});
                i++;
            }

            // 将结束小于当前点的全部弹出
            while (!pq.isEmpty() && pq.peek()[0] <= boundary) {
                pq.poll();
            }

            int maxn = pq.isEmpty() ? 0 : pq.peek()[1];
            if (res.size() == 0 || maxn != res.get(res.size() - 1).get(1)) {
                res.add(Arrays.asList(boundary, maxn));
            }
        }

        return res;
    }

    public static void main(String[] args) {
        Leet218_TheSkylineProblem ins = new Leet218_TheSkylineProblem();

        int[][] buildings = {{0,2,3}, {2,5,3}};
        System.out.println(ins.getSkyline(buildings));
    }
}
