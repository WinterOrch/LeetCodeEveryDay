public class Leet1744_CanEatFavCandyOnFavDay {
    public boolean[] canEat(int[] candiesCount, int[][] queries) {
        int numTypes = candiesCount.length;
        long[] sum = new long[numTypes + 1];
        int curSum = 0;

        boolean[] res = new boolean[queries.length];

        int i = 0;
        for (int[] query : queries) {
            int favCandy = query[0] + 1, favDay = query[1] + 1, cap = query[2];

            if (favCandy > curSum) {
                for (int j = curSum + 1; j <= favCandy; ++j) {
                    sum[j] = sum[j - 1] + candiesCount[j - 1];
                }
                curSum = favCandy;
            }

            res[i++] = sum[favCandy - 1] / cap + 1 <= favDay && favDay <= sum[favCandy];
        }

        return res;
    }
}