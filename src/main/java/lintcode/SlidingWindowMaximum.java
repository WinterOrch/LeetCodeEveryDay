package lintcode;

import java.util.*;

/**
 * 通过双端队列求滑动窗口中最大值
 * 保持队列头始终为窗口中最大值
 * created in 17:16 2021/4/11
 */
public class SlidingWindowMaximum {
    void inQueue(Deque<Integer> deque, int num) {
        while(!deque.isEmpty() && deque.peekLast() < num)
            deque.pollLast();

        deque.offerLast(num);
    }

    void outQueue(Deque<Integer> deque, int num) {
        if(deque.peekFirst() == num)
            deque.pollFirst();
    }

    public List<Integer> maximum(int[] nums, int k) {
        List<Integer> ans = new ArrayList<>();
        Deque<Integer> deque = new ArrayDeque<>();

        if(0 == nums.length)
            return ans;

        for(int i = 0; i < k - 1; ++i) {
            inQueue(deque, nums[i]);
        }

        for(int i = k - 1; i < nums.length; ++i) {
            inQueue(deque, nums[i]);
            ans.add(deque.peekFirst());
            outQueue(deque, nums[i - k + 1]);
        }

        return ans;
    }
}
