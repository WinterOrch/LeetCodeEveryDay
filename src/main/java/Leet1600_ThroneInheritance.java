import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Leet1600_ThroneInheritance {
    static class Member {
        String memName;

        Member next;
        Member last;

        boolean isPassed = false;

        Member(String name) {
            this.memName = name;
        }
    }

    Map<String, Member> memberMap;
    Member head;

    public Leet1600_ThroneInheritance(String kingName) {
        Member root = new Member(kingName);
        // Use dummy root and tail
        // they are part of linked list but none of successor order
        root.next = new Member("DEFAULT_TAIL_HAHA");
        this.head = new Member("DEFAULT_HEAD_HAHA");
        this.head.next = root;
        this.memberMap = new HashMap<>();
        this.memberMap.put(kingName, root);
    }

    public void birth(String parentName, String childName) {
        Member newChild = new Member(childName);

        Member parent = this.memberMap.get(parentName);
        Member tmp = parent;

        while (tmp.last != null) {
            tmp = tmp.last;
        }

        newChild.next = tmp.next;
        tmp.next = newChild;
        parent.last = newChild;

        this.memberMap.put(childName, newChild);
    }

    public void death(String name) {
        this.memberMap.get(name).isPassed = true;
    }

    public List<String> getInheritanceOrder() {
        List<String> ans = new ArrayList<>();

        Member tmp = this.head.next;
        while (tmp.next != null) {
            if (!tmp.isPassed) {
                ans.add(tmp.memName);
            }
            tmp = tmp.next;
        }

        return ans;
    }
}
