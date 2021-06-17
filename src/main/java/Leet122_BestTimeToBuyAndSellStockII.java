class Leet122_BestTimeToBuyAndSellStockII {
    public int maxProfit(int[] prices) {
        int res = 0;

        int i = 1;
        while(i < prices.length) {
            if(prices[i] > prices[i - 1]) {
               res += prices[i] - prices[i - 1];
            }
            ++i;
        }

        return res;
    }
}