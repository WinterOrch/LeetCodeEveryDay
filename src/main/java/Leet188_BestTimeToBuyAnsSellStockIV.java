public class Leet188_BestTimeToBuyAnsSellStockIV {
    public int maxProfit(int k, int[] prices) {
        int days = prices.length;
        if (days < 2) {
            return 0;
        }
        if (k >= days) {
            return solveMaxProfit(prices);
        }

        int[] global = new int[k + 1];
        int[] local = new int[k + 1];
        for (int i = 0; i < days - 1; ++i) {
            int diff = prices[i + 1] - prices[i];
            for (int j = k; j >= 1; --j) {
                local[j] = Math.max(global[j - 1] + Math.max(diff, 0), local[j] + diff);
                global[j] = Math.max(global[j], local[j]);
            }
        }
        return global[k];
    }

    private int solveMaxProfit(int[] prices) {
        int res = 0;
        for (int i = 1; i < prices.length; ++i) {
            if (prices[i] > prices[i - 1]) {
                res += prices[i] - prices[i - 1];
            }
        }
        return res;
    }
}
