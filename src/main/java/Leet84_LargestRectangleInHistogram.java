import java.util.ArrayDeque;
import java.util.Deque;

public class Leet84_LargestRectangleInHistogram {
    public int largestRectangleArea(int[] heights) {
        int len = heights.length;

        if(len == 0) {
            return 0;
        }

        Deque<Integer> stack = new ArrayDeque<>();
        stack.addLast(0);
        int res = heights[0];

        for(int i = 1; i < len; ++i) {
            while(!stack.isEmpty() && heights[i] < heights[stack.peekLast()]) {
                int height = heights[stack.removeLast()];

                res = Math.max(res, height * (stack.isEmpty() ? i : (i - stack.peekLast() - 1)));
            }

            //  这里其实无所谓，加的过程在出栈之后，一律进栈就完了
            if(stack.isEmpty() || heights[i] > heights[stack.peekLast()])
                stack.addLast(i);
            else if(heights[i] == heights[stack.peekLast()]) {
                stack.removeLast();
                stack.addLast(i);
            }
        }

        while(!stack.isEmpty()) {
            int h = stack.removeLast();
            res = Math.max(res, (len - h) * heights[h]);

            if(stack.isEmpty()) {
                res = Math.max(res, len * heights[h]);
                break;
            }else {
                res = Math.max(res, (len - stack.peekLast() - 1) * heights[h]);
            }
        }

        return res;
    }

    public int largestRectangleAreaRemastered(int[] heights) {
        int res = 0;

        Deque<Integer> stack = new ArrayDeque<>();

        for(int i = 0; i < heights.length; ++i) {
            while(!stack.isEmpty() && heights[stack.peekLast()] >= heights[i]) {
                int cur = stack.removeLast();
                res = Math.max(res, heights[cur] * (stack.isEmpty() ? i : (i - stack.peekLast() - 1)));
            }
            stack.addLast(i);
        }

        //  这个最后处理其实和前面基本是一样的
        //  可以通过在高度数组最后加一个零来实现
        //  这里就不考虑代码美观问题，单独写出来了
        while(!stack.isEmpty() && heights[stack.peekLast()] >= 0) {
            int cur = stack.removeLast();
            res = Math.max(res,
                    heights[cur] * (stack.isEmpty() ? heights.length : (heights.length - stack.peekLast() - 1)));
        }

        return res;
    }

    public int largestRec(int[] heights) {
        int res = 0;

        int[] stack = new int[heights.length];
        int top = -1;
        for (int i = 0; i < heights.length; ++i) {
            while (top >= 0 && heights[stack[top]] >= heights[i]) {
                int cur = stack[top--];
                res = Math.max(res, heights[cur] * (top == -1 ? i : i - stack[top] - 1));
            }
            stack[++top] = i;
        }

        while (top >= 0 && heights[stack[top]] >= 0) {
            res = Math.max(res, heights[stack[top--]] * (top == -1 ? heights.length : heights.length - stack[top] - 1));
        }

        return res;
    }

    public static void main(String[] args) {
        int[] histogram = {2,1,5,6,2,3};

        Leet84_LargestRectangleInHistogram ins = new Leet84_LargestRectangleInHistogram();

        System.out.println(ins.largestRectangleArea(histogram));
    }
}
