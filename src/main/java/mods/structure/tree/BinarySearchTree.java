package mods.structure.tree;

import mods.structure.tree.node.TreeNode;

import java.util.Objects;

public class BinarySearchTree {
    public static final int NULL_VALUE = Integer.MAX_VALUE;

    TreeNode head = null;

    public BinarySearchTree() {
    }

    public BinarySearchTree(int[] inputValues) {
        if(null == inputValues)
            return;

        for(int value : inputValues) {
            if(BinarySearchTree.NULL_VALUE != value) {
                this.insert(value);
            }
        }
    }

    public TreeNode search(int value) {
        if(null == this.head)
            return null;

        return searchIfExists(this.head, value, false);
    }

    public void insert(int value) {
        if(null == this.head) {
            this.head = new TreeNode(value);
        }
        else {
            TreeNode pos = Objects.requireNonNull(searchIfExists(this.head, value, true));
            if(pos.val > value) {
                pos.left = new TreeNode(value);
            }
            else {
                pos.right = new TreeNode(value);
            }
        }
    }

    private TreeNode searchIfExists(TreeNode curHead, int value, boolean nearest) {
        if(value == curHead.val)
            return curHead;

        if(value < curHead.val && null != curHead.left) {
            return searchIfExists(curHead.left, value, nearest);
        }else if(value > curHead.val && null != curHead.right) {
            return searchIfExists(curHead.right, value, nearest);
        }

        if(nearest)
            return curHead;
        else
            return null;
    }
}
