package jianzhi;

import mods.structure.tree.node.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

public class _37 {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null)
            return "[]";
        StringBuilder res = new StringBuilder("[");
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if(node.val != Integer.MIN_VALUE) {
                res.append(node.val).append(",");

                queue.add((node.left == null) ? new TreeNode(Integer.MIN_VALUE) : node.left);
                queue.add((node.right == null) ? new TreeNode(Integer.MIN_VALUE) : node.right);
            }
            else
                res.append("null,");
        }
        res.deleteCharAt(res.length() - 1);
        res.append("]");
        return res.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.equals("[]"))
            return null;

        String[] val_arr = data.substring(1, data.length() - 1).split(",");
        TreeNode root = new TreeNode(Integer.parseInt(val_arr[0]));
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int i = 1;
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if(!val_arr[i].equals("null")) {
                node.left = new TreeNode(Integer.parseInt(val_arr[i]));
                queue.add(node.left);
            }
            i++;
            if(!val_arr[i].equals("null")) {
                node.right = new TreeNode(Integer.parseInt(val_arr[i]));
                queue.add(node.right);
            }
            i++;
        }
        return root;
    }
}
