package jianzhi;

public class _35 {
    public Node copyRandomList(Node head) {
        if (head == null)
            return null;

        Node newHead = new Node(head.val);
        newHead.next = head.next;
        head.next = newHead;

        Node current = newHead.next;
        while (current != null) {
            Node newNext = new Node(current.val);
            newNext.next = current.next;
            current.next = newNext;
            current = newNext.next;
        }

        current = head;
        while (current != null) {
            if (current.random != null)
                current.next.random = current.random.next;
            current = current.next.next;
        }

        current = head;
        while (current != null) {
            Node newNext = current.next;
            current.next = newNext.next;
            if (newNext.next != null) {
                newNext.next = newNext.next.next;
            }
            current = current.next;
        }

        return newHead;
    }

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
}
