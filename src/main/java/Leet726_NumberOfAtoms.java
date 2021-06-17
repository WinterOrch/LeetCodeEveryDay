import java.util.*;

public class Leet726_NumberOfAtoms {
    public String countOfAtoms(String formula) {
        Map<String, Integer> map = parse(formula, 0, formula.length() - 1);

        Set<String> treeSet = new TreeSet<>(String::compareTo);
        treeSet.addAll(map.keySet());

        StringBuilder res = new StringBuilder();
        for(String name : treeSet) {
            res.append(name);

            if(1 != map.get(name)) {
                res.append(map.get(name));
            }
        }

        return res.toString();
    }

    private Map<String, Integer> parse(String formula, int start, int end) {
        Map<String, Integer> map = new HashMap<>();

        for(int i = start; i <= end; ++i) {
            char tmp = formula.charAt(i);

            if('(' == tmp) {
                int cnt = 1;

                // 找到完整括号，递归
                int s = ++i;
                while(cnt > 0) {
                    if('(' == formula.charAt(i))
                        ++cnt;
                    else if(')' == formula.charAt(i))
                        --cnt;
                    ++i;
                }
                int e = i - 1;
                Map<String, Integer> out = parse(formula, s, e);
                int coefficient;

                // 看括号后的系数（没有就算1）
                if(i <= end) {
                    char nxt = formula.charAt(i);
                    if('0' <= nxt && '9' >= nxt) {
                        s = i++;
                        while(i <= end) {
                            nxt = formula.charAt(i);
                            if('0' <= nxt && '9' >= nxt)
                                ++i;
                            else
                                break;
                        }
                        coefficient = Integer.parseInt(formula.substring(s, i));
                    }else
                        coefficient = 1;
                }else
                    coefficient = 1;

                // 把递归结果并入主枝
                for(Map.Entry<String, Integer> entry : out.entrySet()) {
                    if(map.containsKey(entry.getKey())) {
                        map.compute(entry.getKey(), (k, v) -> {
                            v += entry.getValue() * coefficient;
                            return v;
                        });
                    }else {
                        map.put(entry.getKey(), entry.getValue() * coefficient);
                    }
                }

                // 跑过了，拽回来
                --i;

            }else if(tmp >= 'A' && tmp <= 'Z') {
                String name;
                int coefficient;

                int s = i++;
                while(i <= end) {
                    tmp = formula.charAt(i);
                    if(tmp >= 'a' && tmp <= 'z')
                        ++i;
                    else
                        break;
                }
                name = formula.substring(s, i);

                if(i <= end && '0' <= tmp && '9' >= tmp) {
                    s = i++;
                    while(i <= end) {
                        tmp = formula.charAt(i);
                        if('0' <= tmp && '9' >= tmp)
                            ++i;
                        else
                            break;
                    }
                    coefficient = Integer.parseInt(formula.substring(s, i));
                }else
                    coefficient = 1;

                if(map.containsKey(name)) {
                    map.compute(name, (k, v) -> {
                        v += coefficient;
                        return v;
                    });
                }else
                    map.put(name, coefficient);

                --i;

            }
        }

        return map;
    }

    public static void main(String[] args) {
        Leet726_NumberOfAtoms ins = new Leet726_NumberOfAtoms();

        System.out.println(ins.countOfAtoms("K4(ON(SO3)2)2"));
    }
}
