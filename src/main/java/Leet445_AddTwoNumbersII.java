import mods.structure.list.ListNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class Leet445_AddTwoNumbersII {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Deque<Integer> stack1 = new ArrayDeque<>(), stack2 = new ArrayDeque<>();
        while(l1 != null) {
            stack1.addLast(l1.val);
            l1 = l1.next;
        }
        while(l2 != null) {
            stack2.addLast(l2.val);
            l2 = l2.next;
        }

        int sum = 0;
        ListNode res = new ListNode(0);
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            if(!stack1.isEmpty()) {
                sum += stack1.removeLast();
            }
            if(!stack2.isEmpty()) {
                sum += stack2.removeLast();
            }

            res.val = sum % 10;
            ListNode head = new ListNode(sum / 10);
            head.next = res;
            res = head;
            sum /= 10;
        }
        return res.val == 0 ? res.next : res;
    }
}
