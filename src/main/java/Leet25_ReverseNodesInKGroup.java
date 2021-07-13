import mods.structure.list.ListNode;

public class Leet25_ReverseNodesInKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k == 1 || head == null)
            return head;

        ListNode current = head;

        for (int i = 1; i < k; ++i) {
            if (current.next != null) {
                current = current.next;
            } else {
                return head;
            }
        }

        ListNode last = head, next2Last = last.next;
        current.next = reverseKGroup(current.next, k);
        last.next = current.next;
        current = current.next;

        while (next2Last != current) {
            ListNode temp = next2Last.next;
            next2Last.next = last;
            last = next2Last;
            next2Last = temp;
        }

        return last;
    }

    public static void main(String[] args) {
        Leet25_ReverseNodesInKGroup ins = new Leet25_ReverseNodesInKGroup();

        ins.reverseKGroup(ListNode.generate(new int[]{1,2,3,4,5}), 2);
    }
}
