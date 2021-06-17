import mods.structure.tree.node.TreeNode;

import java.util.*;

public class Leet102_BinaryTreeLevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        levelOrderTraverse(root, 0, res);
        return res;
    }
    private void levelOrderTraverse(TreeNode node, int level, List<List<Integer>> res) {
        if(null == node)
            return;
        if(res.size() == level)
            res.add(new ArrayList<>());
        res.get(level).add(node.val);
        if(null != node.left)
            levelOrderTraverse(node.left, level + 1, res);
        if(null != node.right)
            levelOrderTraverse(node.right, level + 1, res);
    }

    /**
     * 使用队列的写法还是没有迭代快
     * created in 14:37 2021/3/22
     */
    public List<List<Integer>> levelOrderWithQueue(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();

        Deque<TreeNode> queue = new LinkedList<>();
        boolean hasNextLevel = (null != root);
        queue.addLast(root);

        TreeNode cur;
        while(hasNextLevel) {
            List<Integer> output = new ArrayList<>();

            queue.addLast(null);
            while(!queue.isEmpty() && null != queue.peekFirst()) {
                cur = queue.pollFirst();
                output.add(cur.val);

                if(null != cur.left) {
                    queue.addLast(cur.left);
                }

                if(null != cur.right) {
                    queue.addLast(cur.right);
                }
            }

            res.add(output);
            queue.removeFirst();
            hasNextLevel = !queue.isEmpty();
        }

        return res;
    }

}
