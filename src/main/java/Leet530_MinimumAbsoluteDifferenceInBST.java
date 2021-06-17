import mods.structure.tree.node.TreeNode;

public class Leet530_MinimumAbsoluteDifferenceInBST {
    public int minDiffInBST(TreeNode root) {
        if(null == root)
            return 0;

        TreeNode cur, pre;
        cur = root;

        int preVal = -1;
        int res = Integer.MAX_VALUE;

        while(null != cur) {
            if(null == cur.left) {
                if(-1 != preVal) {
                    res = Math.min(res, cur.val - preVal);
                }

                preVal = cur.val;
                cur = cur.right;
            } else {
                pre = cur.left;
                while(pre.right != null && pre.right != cur)
                    pre = pre.right;
                if(null == pre.right) {
                    pre.right = cur;
                    cur = cur.left;
                }else {
                    pre.right = null;

                    if(-1 != preVal) {
                        res = Math.min(res, cur.val - preVal);
                    }

                    preVal = cur.val;
                    cur = cur.right;
                }
            }
        }

        return res;
    }

}
