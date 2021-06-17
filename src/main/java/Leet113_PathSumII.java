import mods.structure.tree.node.TreeNode;

import java.util.LinkedList;
import java.util.List;

public class Leet113_PathSumII {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> out = new LinkedList<>();
        helper(root, targetSum, out, res);
        return res;
    }

    private void helper(TreeNode node, int sum, List<Integer> out, List<List<Integer>> res) {
        if(null == node)
            return;
        out.add(node.val);
        if(node.val == sum && null == node.left && null == node.right)
            res.add(new LinkedList<>(out));
        helper(node.left, sum - node.val, out, res);
        helper(node.right, sum - node.val, out, res);
        out.remove(out.size() - 1);
    }
}
