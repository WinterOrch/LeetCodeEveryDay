public class Leet123_BestTimeToBuyAndSellStockIII {
    public int maxProfit(int[] prices) {
        if(prices.length < 2)
            return 0;

        int[] g = new int[3];
        int[] l = new int[3];

        for(int i = 0; i < prices.length - 1; ++i) {
            int profit = prices[i + 1] - prices[i];
            for(int j = 2; j >= 1; --j) {
                l[j] = Math.max(g[j - 1] + ((profit > 0) ? profit : 0), l[j] + profit);
                g[j] = Math.max(l[j], g[j]);
            }
        }

        return g[2];
    }
}
