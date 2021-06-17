public class Leet1738_FindKthLargestXorCoordinateValue {
    public int kthLargestValue(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        int[][] pre = new int[m + 1][n + 1];
        
        int[] results = new int[m * n];
        int index = 0;
        
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                pre[i][j] = pre[i - 1][j] ^ pre[i][j - 1] ^ pre[i - 1][j - 1] ^ matrix[i - 1][j - 1];
                results[index++] = pre[i][j];
            }
        }

        nthElement(results, 0, k - 1, m * n - 1);
        return results[k - 1];
    }

    public void nthElement(int[] results, int left, int kth, int right) {
        if (left == right) {
            return;
        }
        int pivot = (int) (left + Math.random() * (right - left + 1));
        swap(results, pivot, right);

        int sepl = left - 1, sepr = left - 1;
        for (int i = left; i <= right; i++) {
            if (results[i] > results[right]) {
                swap(results, ++sepr, i);
                swap(results, ++sepl, sepr);
            } else if (results[i] == results[right]) {
                swap(results, ++sepr, i);
            }
        }

        if (left + kth <= sepl) {
            nthElement(results, left, kth, sepl);
        } else if (left + kth > sepr) {
            nthElement(results, sepr + 1, kth - (sepr - left + 1), right);
        }
    }

    public void swap(int[] results, int index1, int index2) {
        int temp = results[index1];
        results[index1] = results[index2];
        results[index2] = temp;
    }
}
