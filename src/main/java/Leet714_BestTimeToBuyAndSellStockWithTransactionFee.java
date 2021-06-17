public class Leet714_BestTimeToBuyAndSellStockWithTransactionFee {
    public int maxProfit(int[] prices, int fee) {
        int sold = 0;
        int hold = -prices[0];

        for(int price : prices) {
            int preSold = sold;
            sold = Math.max(sold, hold + price - fee);
            hold = Math.max(hold, preSold - price);
        }

        return sold;
    }
}
