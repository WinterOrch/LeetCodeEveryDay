public class Leet121_BestTimeToBuyAndSellStock {
    public int bestSell(int[] prices) {
        int in = prices[0];
        int max = 0;

        int i = 0;
        while(i < prices.length) {
            if(prices[i] < in) {
                in = prices[i];
            }

            max = Math.max(max, prices[i] - in);

            ++i;
        }

        return max;
    }
}
