import mods.structure.tree.node.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

public class Leet101_SymmetricTree {
    public boolean isSymmetric(TreeNode root) {
        return null == root || checkSymmetric(root.left, root.right);
    }

    private boolean checkSymmetric(TreeNode left, TreeNode right) {
        return (left == null && right == null) ||
                (left != null && right != null) &&
                        (left.val == right.val) &&
                        (checkSymmetric(left.left, right.right) && checkSymmetric(left.right, right.left));
    }

    /**
     * 事实证明栈时间空间效率都不如迭代
     * created in 11:34 2021/3/22
     */
    public boolean isSymmetricByStack(TreeNode root) {
        if(null == root)
            return true;

        Deque<TreeNode> q1 = new LinkedList<>();
        Deque<TreeNode> q2 = new LinkedList<>();
        q1.addFirst(root.left);
        q2.addFirst(root.right);

        while(!q1.isEmpty() && !q2.isEmpty()) {
            TreeNode node_left = q1.removeFirst();
            TreeNode node_right = q2.removeFirst();

            if(null == node_left && null == node_right)
                continue;
            else if(null == node_left || null == node_right)
                return false;

            if(node_left.val != node_right.val)
                return false;

            q1.addFirst(node_left.left);
            q1.addFirst(node_left.right);
            q2.addFirst(node_right.right);
            q2.addFirst(node_right.left);
        }

        return true;
    }
}
