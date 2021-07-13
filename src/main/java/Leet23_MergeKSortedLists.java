import mods.structure.list.ListNode;

public class Leet23_MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        int n = lists.length;

        if (n == 0) {
            return null;
        }

        while (n > 1) {
            int k = (n + 1) / 2;

            for (int i = 0; i < n / 2; ++i) {
                lists[i] = mergeSortedLists(lists[i], lists[i + k]);
            }

            n = k;
        }

        return lists[0];
    }

    private ListNode mergeSortedLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }

        current.next = (l1 == null) ? l2 : l1;
        return dummy.next;
    }
}
