import java.util.ArrayDeque;
import java.util.Deque;

public class Leet138_CopyListWithRandomPointer {
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    /**
     * 新建的链表插在原链表每两个结点之间
     * 遍历第二遍修改random
     * 第三遍恢复原链表
     * created in 21:18 2021/4/12
     */
    public Node copyRandomList(Node head) {
        if(null == head)
            return null;

        Node cur = head;

        while(null != cur) {
            Node tmp = new Node(cur.val);
            tmp.next = cur.next;
            cur.next = tmp;
            cur = tmp.next;
        }

        cur = head;
        Node dummy = head.next;
        while(null != cur) {
            if(null != cur.random) {
                cur.next.random = cur.random.next;
            }

            cur = cur.next.next;
        }

        cur = head;
        while(null != cur) {
            Node tmp = cur.next;
            cur.next = tmp.next;
            if(null != tmp.next)
                tmp.next = tmp.next.next;
            cur = cur.next;
        }

        return dummy;
    }
}
