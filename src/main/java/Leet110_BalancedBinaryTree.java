import mods.structure.tree.node.TreeNode;

public class Leet110_BalancedBinaryTree {
    public boolean isBalanced(TreeNode root) {
        return !(checkBalance(root) == -1);
    }
    private int checkBalance(TreeNode root) {
        if(null == root)
            return 0;
        int right = checkBalance(root.right);
        if(-1 == right)
            return -1;
        int left = checkBalance(root.left);
        if(-1 == left)
            return -1;

        if(Math.abs(left - right) > 1)
            return -1;
        else
            return 1 + Math.max(left, right);
    }
}
