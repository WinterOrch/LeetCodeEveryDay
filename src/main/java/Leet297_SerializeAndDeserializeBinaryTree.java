import mods.structure.tree.node.TreeNode;

public class Leet297_SerializeAndDeserializeBinaryTree {
    private int ptr;

    public String serialize(TreeNode root) {
        if (root == null) {
            return "X";
        }
        String left = "(" + serialize(root.left) + ")";
        String right = "(" + serialize(root.right) + ")";
        return left + root.val + right;
    }

    public TreeNode deserialize(String data) {
        ptr = 0;
        return parse(data.toCharArray());
    }

    public TreeNode parse(char[] data) {
        if (data[ptr] == 'X') {
            ++ptr;
            return null;
        }
        TreeNode cur = new TreeNode(0);
        cur.left = parseSubtree(data);
        cur.val = parseInt(data);
        cur.right = parseSubtree(data);
        return cur;
    }

    public TreeNode parseSubtree(char[] data) {
        ++ptr; // 跳过左括号
        TreeNode subtree = parse(data);
        ++ptr; // 跳过右括号
        return subtree;
    }

    public int parseInt(char[] data) {
        int x = 0, sgn = 1;
        if (!Character.isDigit(data[ptr])) {
            sgn = -1;
            ++ptr;
        }
        while (Character.isDigit(data[ptr])) {
            x = x * 10 + data[ptr++] - '0';
        }
        return x * sgn;
    }
}
