public class Leet852_PeakIndexInAMountainArray {
    public int peakIndexInMountainArray(int[] arr) {
        int left = 0, right = arr.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] < arr[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    public static void main(String[] args) {
        int[] inputs = {0,1,0};

        Leet852_PeakIndexInAMountainArray ins = new Leet852_PeakIndexInAMountainArray();
        System.out.println(ins.peakIndexInMountainArray(inputs));
    }
}
