public class Leet1442_CountTripletsThatCanFormTwoArraysOfEqualXor {
    public int countTriplets(int[] arr) {
        int n = arr.length;
        if (n <= 1)
            return 0;

        int[] s = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] ^ arr[i];
        }

        int res = 0;
        for (int i = 0; i < n; ++i) {
            for (int k = i + 1; k < n; ++k) {
                if (s[i] == s[k + 1])
                    res += k - i;
            }
        }

        return res;
    }
}
