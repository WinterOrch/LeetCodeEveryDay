public class Leet768_MaxChunksToMakeSortedII {
    public int maxChunksToSorted(int[] arr) {
        int[] stack = new int[arr.length];
        int top = -1;

        for (int num : arr) {
            if (top < 0 || stack[top] <= num) {
                stack[++top] = num;
            } else {
                int curMax = stack[top--];
                while (top >= 0 && stack[top] > num) {
                    --top;
                }
                stack[++top] = curMax;
            }
        }

        return top + 1;
    }

    public static void main(String[] args) {
        Leet768_MaxChunksToMakeSortedII ins = new Leet768_MaxChunksToMakeSortedII();
        System.out.println(ins.maxChunksToSorted(new int[]{0,3,0,3,2}));
    }
}
