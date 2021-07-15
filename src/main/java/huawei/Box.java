package huawei;

public class Box {

    private int push(int[] boxes) {

        int[] stack = new int[boxes.length];
        int top = -1;

        int res = 0;

        for (int box : boxes) {
            if (top < 0 || stack[top] < box) {
                stack[++top] = box;
            } else {
                res += stack[top - box];
                while (top >= 0 && stack[top] > box) {
                    --top;
                }
            }
        }

        return res;
    }
}
