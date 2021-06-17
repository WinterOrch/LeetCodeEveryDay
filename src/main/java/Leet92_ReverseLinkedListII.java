import mods.structure.list.ListNode;

public class Leet92_ReverseLinkedListII {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(Integer.MIN_VALUE, head);
        ListNode pre = dummy;

        for (int i = 0; i < left - 1; ++i)
            pre = pre.next;

        ListNode cur = pre.next;
        // 翻转链表的核心——cur始终指向原第一个node
        // pre始终指向原前一个node
        // 不断将cur的后一node插到pre位置
        for (int i = left; i < right; ++i) {
            ListNode tmp = cur.next;
            cur.next = tmp.next;
            tmp.next = pre.next;
            pre.next = tmp;
        }

        return dummy.next;
    }
}
