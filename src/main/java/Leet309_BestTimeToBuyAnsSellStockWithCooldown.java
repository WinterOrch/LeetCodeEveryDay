public class Leet309_BestTimeToBuyAnsSellStockWithCooldown {
    public int maxProfit(int[] prices) {
        int buy = Integer.MIN_VALUE, preBuy;
        int sell = 0, preSell = 0;

        for (int price : prices) {
            preBuy = buy;
            // 这个 preSell 其实是 i - 2 天的
            buy = Math.max(preSell - price, preBuy);

            // i - 1 天的 preSell
            preSell = sell;
            sell = Math.max(preBuy + price, preSell);
        }

        return sell;
    }
}
