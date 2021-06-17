import mods.structure.tree.node.TreeNode;

public class Leet99_RecoverBinarySearchTree {
    void recoverTree(TreeNode root) {
        TreeNode susNode_1 = null;
        TreeNode susNode_2 = null;
        boolean cfm = false;

        TreeNode cur, pre;
        cur = root;

        while(null != cur) {
            if(null != cur.left){
                pre = cur.left;
                while(null != pre.right && cur != pre.right)
                    pre = pre.right;

                if(null == pre.right) {
                    pre.right = cur;
                    cur = cur.left;

                    continue;
                }else
                    pre.right = null;
            }

            if(null != susNode_1 && susNode_1.val > cur.val) {
                susNode_2 = cur;

                if(!cfm)
                    cfm = true;
            }

            if(!cfm)
                susNode_1 = cur;
            cur = cur.right;
        }

        if(null != susNode_1 && null != susNode_2) {
            int temp = susNode_1.val;
            susNode_1.val = susNode_2.val;
            susNode_2.val = temp;
        }
    }

}
