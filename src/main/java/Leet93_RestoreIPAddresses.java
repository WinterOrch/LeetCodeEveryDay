import java.util.ArrayList;
import java.util.List;

public class Leet93_RestoreIPAddresses {
    public static List<String> restoreIpAddresses(String s) {
        StringBuilder out = new StringBuilder();
        List<String> output = new ArrayList<>();
        
        check(s, 4, out, output);
        return output;
    }

    private static void check(String remain, int cnt, StringBuilder cur, List<String> output) {
        int n = remain.length();
        String curTest;

        if(n <= cnt * 3 && n >= cnt) {

            if(4 == cnt)
                curTest = "";
            else
                curTest = ".";

            if(1 == cnt) {
                if(isLegal(remain)) {
                    curTest += remain;
                    cur.append(curTest);
                    output.add(cur.toString());
                    cur.delete(cur.length() - curTest.length(), cur.length());
                }
            }else if(n == cnt || remain.charAt(0) == '0') {
                curTest += remain.substring(0, 1);
                cur.append(curTest);

                check(remain.substring(1), cnt - 1, cur, output);
                cur.delete(cur.length() - curTest.length(), cur.length());
            }else {
                int l = 1;
                while(l < n && l <= 3 && isLegal(remain.substring(0, l))) {
                    curTest += remain.substring(0, l);
                    cur.append(curTest);

                    check(remain.substring(l), cnt - 1, cur, output);
                    cur.delete(cur.length() - curTest.length(), cur.length());

                    if(4 == cnt)
                        curTest = "";
                    else
                        curTest = ".";

                    ++l;
                }
            }
        }
    }

    private static boolean isLegal(String remain) {
        if(remain.isEmpty())
            return false;
        if(remain.startsWith("0"))
            return 1 == remain.length();
        else {
            int x = Integer.parseInt(remain);
            return (x <= 255);
        }
    }

    public static void main(String[] args) {
        List<String> output = Leet93_RestoreIPAddresses.restoreIpAddresses("101023");

        for(String s : output) {
            System.out.println(s);
        }

    }
}
