package mods;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BinaryTraverse {
    /**
     * Morris 的关键在于如何一边遍历一边消除对原树的改动
     * 前序遍历中，这一方法是直接将当前节点挂到其中序遍历下的前驱节点右孩子
     * 这样在再次检索前驱节点时会回环到自身，从而发现是人为改动过的节点，继续遍历同时予以消除
     * created in 18:15 2021/3/20
     */
    public List<Integer> preorderMorrisTraverse(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        if(null == root)
            return res;

        TreeNode cur, pre;

        cur = root;
        while(null != cur) {
            if(null == cur.left) {
                res.add(cur.value);
                cur = cur.right;
            }else {
                pre = cur;
                while(null != pre.left && null == pre.left.right) {
                    pre = pre.left;
                }

                if(null != pre.left){
                    pre = pre.left;
                    while(null != pre.right && cur != pre.right) {
                        pre = pre.right;
                    }
                }

                if(null == pre.right) {
                    pre.right = cur;
                    res.add(cur.value);
                    cur = cur.left;
                }else if(cur == pre.right) {
                    pre.right = null;
                    cur = cur.right;
                }
            }
        }

        return res;
    }

    /**
     * 中序Morris
     * created in 18:15 2021/3/20
     */
    public List<Integer> morrisTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        if(null == root)
            return res;

        TreeNode cur, pre;
        cur = root;

        while(null != cur) {
            if(null == cur.left) {
                res.add(cur.value);
                cur = cur.right;
            }else {
                pre = cur.left;
                while(null != pre.right && cur != pre.right)
                    pre = pre.right;

                if(null == pre.right) {
                    pre.right = cur;
                    cur = cur.left;
                }else {
                    pre.right = null;
                    res.add(cur.value);
                    cur = cur.right;
                }
            }
        }

        return res;
    }

    /**
     * 后序非常非常复杂
     * created in 18:37 2021/3/20
     */
    public List<Integer> postorderMorrisTraverse(TreeNode root) {
        List<Integer> res = new LinkedList<>();

        TreeNode dummy = new TreeNode(-1);
        dummy.left = root;

        TreeNode cur = dummy;
        TreeNode pre;

        while(null != cur) {
            if(null == cur.left) {
                pre = cur;
                cur = cur.right;
            }else {
                // TODO
            }
        }

        return res;
    }


}

class TreeNode {
    TreeNode(int value) {
        this.value = value;
    }

    public TreeNode left;
    public TreeNode right;

    public int value;
}
