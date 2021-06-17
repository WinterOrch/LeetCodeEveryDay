public class Leet4_MedianOfTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        return (findKth(nums1, nums2, (m + n + 1) / 2, 0, 0)
                + findKth(nums1, nums2, (m + n + 2) / 2, 0, 0)) / 2.0;
    }

    private int findKth(int[] nums1, int[] nums2, int k, int idx_1, int idx_2) {
        if(idx_1 >= nums1.length)
            return nums2[idx_2 + k - 1];
        if(idx_2 >= nums2.length)
            return nums1[idx_1 + k - 1];

        if(1 == k)
            return Math.min(nums1[idx_1], nums2[idx_2]);

        int temp_1 = ( idx_1 + k / 2 - 1 ) < nums1.length ?
                nums1[idx_1 + k / 2 - 1] : Integer.MAX_VALUE;
        int temp_2 = ( idx_2 + k / 2 - 1 ) < nums2.length ?
                nums2[idx_2 + k / 2 - 1] : Integer.MAX_VALUE;

        if(temp_1 > temp_2)
            return findKth(nums1, nums2, k - k / 2, idx_1, idx_2 + k / 2);
        else
            return findKth(nums1, nums2, k - k / 2, idx_1  + k / 2, idx_2);
    }

    public static void main(String[] args) {
        Leet4_MedianOfTwoSortedArrays ins = new Leet4_MedianOfTwoSortedArrays();

        int[] a1 = {1, 3};
        int[] a2 = {2};

        System.out.println(ins.findMedianSortedArrays(a1, a2));
    }
}
