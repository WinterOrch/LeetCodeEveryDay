import java.util.ArrayDeque;
import java.util.Deque;

public class Leet85_MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        //  OJ第一个用例居然就是空数组，吓了一跳
        if(matrix.length < 1)
            return 0;

        int res = 0;

        int width = matrix[0].length;

        int[] heights = new int[width + 1];
        heights[width] = 0;

        // 生成上一题的那种柱状图，给子方法用
        // 虽然方法跟DP没关系，生成柱状图的方法倒是有点DP的意思
        for (char[] aMatrix : matrix) {
            for (int j = 0; j < width; ++j) {
                heights[j] = aMatrix[j] == '0' ? 0 : (1 + heights[j]);
            }
            res = Math.max(res, largestRectangleArea(heights));
        }

        return res;
    }

    /**
     * 直接把上一题的方法拿来用
     * created in 20:16 2021/4/14
     */
    private int largestRectangleArea(int[] height) {
        int res = 0;
        Deque<Integer> stack = new ArrayDeque<>();

        for(int i = 0; i < height.length; ++i) {
            while(!stack.isEmpty() && height[stack.peekLast()] >= height[i]) {
                int cur = stack.removeLast();
                res = Math.max(res, height[cur] * (stack.isEmpty() ? i : (i - stack.peekLast() - 1)));
            }
            stack.addLast(i);
        }

        return res;
    }
}
