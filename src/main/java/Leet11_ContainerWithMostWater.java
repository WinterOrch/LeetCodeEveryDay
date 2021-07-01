public class Leet11_ContainerWithMostWater {
    public int maxArea(int[] height) {
        int res = 0;

        int i = 0, j = height.length - 1;
        while (i < j) {
            int area = (j - i) * ((height[i] < height[j]) ? height[i++] : height[j--]);
            res = Math.max(res, area);
        }

        return res;
    }

    public static void main(String[] args) {
        Leet11_ContainerWithMostWater ins = new Leet11_ContainerWithMostWater();
        System.out.println(ins.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
    }
}
