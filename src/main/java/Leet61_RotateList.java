import mods.ListNode;

public class Leet61_RotateList {
    public ListNode rotateRight(ListNode head, int k) {
        if(0 == k || null == head)
            return head;

        ListNode curNode = head;

        int lenList = 1;
        while(null != curNode.next) {
            lenList++;
            curNode = curNode.next;
        }

        curNode.next = head;

        if(k >= lenList)
            k %= lenList;

        k = (lenList - k) % lenList;

        for(int i = 0; i < k; ++i) {
            curNode = curNode.next;
        }

        head = curNode.next;
        curNode.next = null;

        return head;
    }
}
