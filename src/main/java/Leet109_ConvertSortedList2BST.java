import mods.structure.list.ListNode;
import mods.structure.tree.node.TreeNode;

public class Leet109_ConvertSortedList2BST {
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null)
            return null;
        return helper(head, null);
    }

    private TreeNode helper (ListNode head, ListNode tail) {
        if (head == tail)
            return null;
        ListNode s = head, f = head;
        while (f != tail && f.next != tail) {
            s = s.next;
            f = f.next.next;
        }

        TreeNode cur = new TreeNode(s.val);
        cur.left = helper(head, s);
        cur.right = helper(s.next, tail);

        return cur;
    }
}
