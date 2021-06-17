package mods;

import java.util.Arrays;

public class Sort {
    /**
     * 插入排序
     * created in 11:05 2021/4/15
     */
    public static void insertionSort(int[] a, int n) {
        if(n <= 1)
            return;

        for(int i = 1; i < n; ++i) {
            int value = a[i];
            int j = i - 1;

            for(; j >= 0; --j) {
                if(a[j] > value) {
                    a[j + 1] = a[j];
                }else {
                    break;
                }
            }

            a[j + 1] = value;
        }
    }

    /**
     * 归并排序
     * created in 11:05 2021/4/15
     */
    public static void mergeSort(int[] a, int n) {
        mergeSortDiv(a, 0, n - 1);
    }

    private static void mergeSortDiv(int[] a, int start, int end) {
        if(start >= end)
            return;

        int mid = (start + end) / 2;

        mergeSortDiv(a, start, mid);
        mergeSortDiv(a, mid + 1, end);

        merge(a, start, mid, end);
    }

    private static void merge(int[] a, int start, int mid, int end) {
        int i = start, j = mid + 1, k = 0;

        int[] tmp = new int[end - start + 1];

        while(i <= mid && j <= end) {
            if(a[i] <= a[j])
                tmp[k++] = a[i++];
            else
                tmp[k++] = a[j++];
        }

        int rs = i, re = mid;
        if(j <= end) {
            rs = j;
            re = end;
        }

        while(rs <= re) {
            tmp[k++] = a[rs++];
        }

        System.arraycopy(tmp, 0, a, start, tmp.length);
    }

    /**
     * 快速排序
     * created in 11:38 2021/4/15
     */
    public static void quickSort(int[] a, int n) {
        quickSortDiv(a, 0, n - 1);
    }

    private static void quickSortDiv(int[] a, int start, int end) {
        if(start >= end)
            return;

        int mid = partition(a, start, end);
        quickSortDiv(a, start, mid - 1);
        quickSortDiv(a, mid + 1, end);
    }

    private static int partition(int[] a, int start, int end) {
        int pivot = a[end];
        int i = pivot;
        int j;

        for(j = start; j < end; ++j) {
            if(a[j] < pivot) {
                swap(a, i++, j);
            }
        }

        swap(a, i, j);
        return i;
    }

    private static void swap(int[] a, int idx_1, int idx_2) {
        int tmp = a[idx_1];
        a[idx_1] = a[idx_2];
        a[idx_2] = tmp;
    }

    public static void main(String[] args) {
        int[] array = {1, 4, 3, 2, 6};

        Sort.insertionSort(array, array.length);
        System.out.println(Arrays.toString(array));
    }
}
