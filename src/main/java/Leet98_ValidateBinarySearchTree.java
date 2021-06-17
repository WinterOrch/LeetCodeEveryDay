import mods.structure.tree.node.TreeNode;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
public class Leet98_ValidateBinarySearchTree {
    public boolean isValidBST(TreeNode root) {
        return null == root || valid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean valid(TreeNode root, long low, long high) {
        return null == root
                || root.val > low && root.val < high
                && valid(root.left, low, root.val) && valid(root.right, root.val, high);
    }
}