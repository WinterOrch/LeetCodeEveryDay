import mods.structure.list.ListNode;

public class Leet86_PartitionList {
    public ListNode partition(ListNode head, int x) {
        ListNode dummy = new ListNode(Integer.MIN_VALUE, head);
        ListNode curNode = dummy;

        ListNode tail = null;

        while(curNode != null) {
            if(curNode.val < x) {
                tail = curNode;
                curNode = curNode.next;
            } else {
                break;
            }
        }

        ListNode pre = tail;
        while(curNode != null) {
            if(curNode.val < x) {
                pre.next = curNode.next;

                ListNode tmp = curNode;
                curNode = curNode.next;

                tmp.next = tail.next;
                tail.next = tmp;
                tail = tmp;
            } else {
                pre = curNode;
                curNode = curNode.next;
            }
        }

        return dummy.next;
    }
}
