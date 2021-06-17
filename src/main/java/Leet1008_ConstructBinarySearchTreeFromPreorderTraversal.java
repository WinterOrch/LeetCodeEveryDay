import mods.structure.tree.node.TreeNode;

public class Leet1008_ConstructBinarySearchTreeFromPreorderTraversal {
    public TreeNode bstFromPreorder(int[] preorder) {
        TreeNode root = new TreeNode(preorder[0]);

        for (int i = 1; i < preorder.length; ++i) {
            hang(root, preorder[i]);
        }

        return root;
    }

    private void hang(TreeNode root, int val) {
        TreeNode pre = root;

        while (root != null) {
            pre = root;
            if (val > root.val) {
                root = root.right;
            } else {
                root = root.left;
            }
        }

        if (val < pre.val) {
            pre.left = new TreeNode(val);
        } else {
            pre.right = new TreeNode(val);
        }
    }
}
