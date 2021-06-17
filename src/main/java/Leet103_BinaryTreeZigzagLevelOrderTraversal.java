import mods.structure.tree.node.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Leet103_BinaryTreeZigzagLevelOrderTraversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        zigzagLevelOrderTraverse(root, 0, res);
        return res;
    }
    private void zigzagLevelOrderTraverse(TreeNode node, int level, List<List<Integer>> res) {
        if(null == node)
            return;
        if(res.size() == level)
            res.add(new LinkedList<>());
        if(level % 2 == 0) {
            res.get(level).add(node.val);
        }else {
            res.get(level).add(0, node.val);
        }

        zigzagLevelOrderTraverse(node.left, level + 1, res);
        zigzagLevelOrderTraverse(node.right, level + 1, res);
    }
}
