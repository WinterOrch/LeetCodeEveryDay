import mods.structure.tree.node.TreeNode;

public class Leet114_FlattenBinaryTreeToLinkedList {
    /**
     * 将整个二叉树按照先序遍历顺序平铺成一根左孩子全部为空的枝
     * created in 18:19 2021/3/20
     */
    public void flatten(TreeNode root) {
        if(null == root)
            return;

        TreeNode cur, pre;

        cur = root;
        while(null != cur.left || null != cur.right) {
            if(null != cur.right) {
                pre = cur;
                while(null != pre.left && null == pre.left.right) {
                    pre = pre.left;
                }

                if(null != pre.left){
                    pre = pre.left;
                    while(null != pre.right) {
                        pre = pre.right;
                    }
                }

                if(cur != pre) {
                    pre.right = cur.right;
                    cur.right = null;
                }else if(null == cur.left)
                    cur = cur.right;
            }else {
                cur.right = cur.left;
                cur.left = null;
                cur = cur.right;
            }
        }
    }
}
