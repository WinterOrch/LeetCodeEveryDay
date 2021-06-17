import mods.structure.tree.node.TreeNode;

public class Leet897_IncreasingOrderSearchTree {
    public TreeNode increasingBST(TreeNode root) {
        TreeNode cur, pre;
        cur = root;

        TreeNode former;

        TreeNode dummy = root;

        while(null != cur) {
            if(null == cur.left) {
                if(null != cur.right && null != cur.right.left) {
                    pre = cur.right.left;
                    while(null != pre.left) {
                        pre = pre.left;
                    }

                    if(null != pre.right) {
                        former = pre.right;
                        while(null != former && cur != former) {
                            former = former.right;
                        }

                        if(cur == former) {
                            cur = cur.right;
                            continue;
                        }
                    }

                    former = cur;
                    cur = cur.right;
                    former.right = pre;
                } else
                    cur = cur.right;
            }
            else {
                pre = cur.left;
                while(null != pre.right && cur != pre.right) {
                    pre = pre.right;
                }

                if(null == pre.right) {
                    pre.right = cur;
                    cur = cur.left;
                    if(cur.val < dummy.val)
                        dummy = cur;
                }else {
                    cur.left = null;
                    cur = cur.right;
                }
            }
        }

        return dummy;
    }

    TreeNode helper(TreeNode node, TreeNode pre) {
        if(null == node)
            return pre;
        TreeNode res = helper(node.left, node);
        node.left = null;
        node.right = helper(node.right, pre);

        return res;
    }
}
