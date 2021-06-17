import mods.structure.tree.node.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class Leet337_HouseRobberIII {
    /**
     * 直接DFS会TLE，那就把查过的点（取该Node时的最大价值）结果保存到一个HashMap中
     * created in 14:18 2021/4/14
     */
    public int rob(TreeNode root) {
        Map<TreeNode, Integer> scanPart = new HashMap<>();

        return Math.max(robThis(root, scanPart), skipThis(root, scanPart));
    }

    private int robThis(TreeNode node, Map<TreeNode, Integer> scanPart) {
        if(scanPart.containsKey(node))
            return scanPart.get(node);

        int maxRob = node.val;
        if(null != node.left) {
            maxRob += skipThis(node.left, scanPart);
        }
        if(null != node.right) {
            maxRob += skipThis(node.right, scanPart);
        }

        scanPart.put(node, maxRob);
        return maxRob;
    }

    private int skipThis(TreeNode node, Map<TreeNode, Integer> scanPart) {
        int maxRob = 0;

        if(null != node.left) {
            maxRob += Math.max(robThis(node.left, scanPart), skipThis(node.left, scanPart));
        }

        if(null != node.right) {
            maxRob += Math.max(robThis(node.right, scanPart), skipThis(node.right, scanPart));
        }

        return maxRob;
    }


    /**
     * 以下为一种传实参的方式，Java实现起来不方便
     * created in 14:39 2021/4/14
     */
    public int robWithHelper(TreeNode root) {
        int[] res = helper(root);
        return Math.max(res[0], res[1]);
    }

    private int[] helper(TreeNode node) {
        if(null == node)
            return new int[2];

        int[] left = helper(node.left);
        int[] right = helper(node.right);

        int[] res = new int[2];
        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        res[1] = left[0] + right[0] + node.val;
        return res;
    }

}
