import mods.structure.list.ListNode;

public class Leet142_LinkedList_CycleII {
    public ListNode detectCycle(ListNode head) {
        if(head == null)
            return null;

        ListNode fast = head, slow = head;

        while(null != fast.next && null != fast.next.next) {
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast)
                break;
        }

        if(null == fast.next || null == fast.next.next)
            return null;

        ListNode tmp = head;
        while(slow != tmp) {
            slow = slow.next;
            tmp = tmp.next;
        }

        return tmp;
    }
}
