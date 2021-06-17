import mods.structure.tree.node.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class Leet173_BinarySearchTreeIterator {
    private final Deque<TreeNode> stack;

    public Leet173_BinarySearchTreeIterator(TreeNode root) {
        this.stack = new ArrayDeque<>();
        while (root != null) {
            stack.addLast(root);
            root = root.left;
        }
    }

    public int next() {
        TreeNode node = this.stack.removeLast();
        int res = node.val;
        if (node.right != null) {
            node = node.right;
            while (node != null) {
                stack.addLast(node);
                node = node.left;
            }
        }
        return res;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }
}
