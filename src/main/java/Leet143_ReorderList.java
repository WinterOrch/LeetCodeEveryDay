import mods.structure.list.ListNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class Leet143_ReorderList {
    /**
     * 要把0 1 2 ... n变成0 n 1 n-1 2 n-2 ...
     * 先找中点
     * 把后半段塞栈里
     * 出栈，插入前半段
     * created in 19:47 2021/4/16
     */
    public void reorderList(ListNode head) {
        if(head == null)
            return;

        ListNode fast = head, slow = head;

        while(fast.next != null) {
            fast = fast.next;
            slow = slow.next;

            if(fast.next != null)
                fast = fast.next;
        }

        if(fast == slow)
            return;

        Deque<ListNode> stack = new ArrayDeque<>();
        fast = slow;
        while(slow.next != null) {
            slow = slow.next;
            stack.addLast(slow);
        }
        fast.next = null;

        slow = head;
        ListNode tmp;
        while(!stack.isEmpty()) {
            tmp = slow;
            slow = slow.next;
            fast = stack.removeLast();
            fast.next = slow;
            tmp.next = fast;
        }
    }

    /**
     * 然而栈还是没有直接逆转快
     * created in 20:23 2021/4/16
     */
    public void reorderList_2(ListNode head) {
        if(head == null)
            return;

        ListNode fast = head, slow = head;

        while(fast.next != null) {
            fast = fast.next;
            slow = slow.next;

            if(fast.next != null)
                fast = fast.next;
        }

        ListNode mid = slow.next;
        slow.next = null;
        ListNode last = mid, pre = null;

        while(last != null) {
            ListNode tmp = last.next;
            last.next = pre;
            pre = last;
            last = tmp;
        }

        slow = head;
        while(pre != null && slow != null) {
            fast = slow.next;
            slow.next = pre;
            pre = pre.next;
            slow.next.next = fast;
            slow = fast;
        }
    }
}
