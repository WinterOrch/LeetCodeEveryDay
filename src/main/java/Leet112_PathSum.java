import mods.structure.tree.node.TreeNode;

public class Leet112_PathSum {

    public boolean hasPathSum(TreeNode root, int targetSum) {
        return null != root && (null == root.left && null == root.right && root.val == targetSum ||
                hasPathSum(root.left, targetSum - root.val) ||
                hasPathSum(root.right, targetSum - root.val));
    }
}
