import mods.structure.tree.node.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Leet95_UniqueBinarySearchTreesII {
    public List<TreeNode> generateTrees(int n) {
        Map<Integer, List<TreeNode>> memo = new HashMap<>();
        return helper(1, n, memo);
    }

    private List<TreeNode> helper(int start, int end, Map<Integer, List<TreeNode>> memo) {
        if (start > end) {
            return null;
        }

        int stateKey = (start << 4) | end;
        if (memo.containsKey(stateKey)) {
            return memo.get(stateKey);
        }

        List<TreeNode> res = new ArrayList<>();
        for (int i = start; i <= end; ++i) {
            List<TreeNode> leftBranches = helper(start, i - 1, memo),
                           rightBranches = helper(i + 1, end, memo);
            if (leftBranches != null && rightBranches != null) {
                for (TreeNode left : leftBranches) {
                    for (TreeNode right :rightBranches) {
                        TreeNode node = new TreeNode(i);
                        node.right = right;
                        node.left = left;
                        res.add(node);
                    }
                }
            } else if (leftBranches == null && rightBranches != null) {
                for (TreeNode right : rightBranches) {
                    TreeNode node = new TreeNode(i);
                    node.right = right;
                    res.add(node);
                }
            } else if (leftBranches != null) {
                for (TreeNode left : leftBranches) {
                    TreeNode node = new TreeNode(i);
                    node.left = left;
                    res.add(node);
                }
            } else {
                TreeNode node = new TreeNode(i);
                res.add(node);
            }
        }
        memo.put(stateKey, res);
        return res;
    }
}
