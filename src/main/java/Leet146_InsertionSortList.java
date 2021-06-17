import mods.structure.list.ListNode;

public class Leet146_InsertionSortList {
    public ListNode insertionSortList(ListNode head) {
        if (null == head)
            return null;

        ListNode curTail = head;

        while (curTail.next != null && curTail.next.val >= curTail.val) {
            curTail = curTail.next;
        }

        while (curTail.next != null) {
            ListNode tmp = curTail.next;
            curTail.next = tmp.next;

            ListNode search = head;
            if (tmp.val < search.val) {
                tmp.next = head;
                head = tmp;
            }else {
                while (search != curTail && tmp.val > search.next.val) {
                    search = search.next;
                }

                tmp.next = search.next;
                search.next = tmp;

                if(search == curTail)
                    curTail = tmp;
            }
        }

        return head;
    }

    public static void main(String[] args) {
        Leet146_InsertionSortList ins = new Leet146_InsertionSortList();

        int[] input = {3, 2, 4};
        ListNode head = ListNode.generate(input);

        ins.insertionSortList(head);
    }
}
