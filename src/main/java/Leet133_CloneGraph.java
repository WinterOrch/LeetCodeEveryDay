import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Leet133_CloneGraph {
    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    public Node cloneGraph(Node node) {
        Map<Node, Node> map = new HashMap<>();
        return helper(node, map);
    }

    private Node helper(Node node, Map<Node, Node> m) {
        if (node == null) {
            return null;
        }
        if (m.containsKey(node)) {
            return m.get(node);
        }

        Node clone = new Node(node.val);
        m.put(node, clone);
        for (Node neighbor : node.neighbors) {
            clone.neighbors.add(helper(neighbor, m));
        }

        return clone;
    }
}
