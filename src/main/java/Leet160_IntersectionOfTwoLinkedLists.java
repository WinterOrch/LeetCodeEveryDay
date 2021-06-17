import mods.structure.list.ListNode;

public class Leet160_IntersectionOfTwoLinkedLists {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (null == headA || null == headB) {
            return null;
        }

        ListNode a = headA, b = headB;
        //  跑到底互换一下跑道，把两条跑道相遇前的差距补足
        while (a != b) {
            a = (a != null) ? a.next : headB;
            b = (b != null) ? b.next : headA;
        }

        return a;
    }
}
