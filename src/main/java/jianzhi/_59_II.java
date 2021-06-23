package jianzhi;

import java.util.Deque;
import java.util.LinkedList;

public class _59_II {
    class MaxQueue {
        Deque<Integer> q;
        Deque<Integer> d;   // 单调递减队列

        public MaxQueue() {
            q = new LinkedList<>();
            d = new LinkedList<>();
        }

        public int max_value() {
            if (d.isEmpty()) {
                return -1;
            }
            return d.peekFirst();
        }

        public void push_back(int value) {
            while (!d.isEmpty() && d.peekLast() < value) {
                d.pollLast();
            }
            // 非严格递减，因此相同最大值会存多份
            d.offerLast(value);
            q.offer(value);
        }

        public int pop_front() {
            if (q.isEmpty()) {
                return -1;
            }
            int ans = q.poll();
            if (ans == d.peekFirst()) {
                d.pollFirst();
            }
            return ans;
        }
    }
}
