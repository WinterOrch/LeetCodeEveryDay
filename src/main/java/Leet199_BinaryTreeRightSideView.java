import mods.structure.tree.node.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Leet199_BinaryTreeRightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        Deque<TreeNode> queue = new LinkedList<>();
        if(null == root)
            return res;
        queue.addLast(root);

        while(!queue.isEmpty()) {
            int num = queue.size();
            for(int i = num; i > 0; --i) {
                TreeNode cur = queue.pollFirst();

                if(i == num)
                    res.add(cur.val);

                if(null != cur.right)
                    queue.addLast(cur.right);
                if(null != cur.left)
                    queue.addLast(cur.left);
            }
        }

        return res;
    }
}
