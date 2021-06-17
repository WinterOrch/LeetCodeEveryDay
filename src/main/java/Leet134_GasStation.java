public class Leet134_GasStation {
    /**
     * 一个非常重要的事实是——只要一圈下来的净耗油大于零就一定有办法以空油箱环绕一圈
     * created in 17:49 2021/5/11
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int diff;

        int tank = 0, sum = 0;

        int index = -1;

        for (int i = 0; i < n; ++i) {
            diff = gas[i] - cost[i];
            tank += diff;
            sum += diff;

            if (tank < 0) {
                index = i;
                tank = 0;
            }
        }

        if (sum < 0 || index == -1)
            return -1;

        for (int i = 0; i < index; ++i) {
            tank += gas[i] - cost[i];
            if (tank < 0)
                return -1;
        }

        return index;
    }

    public static void main(String[] args) {
        Leet134_GasStation ins = new Leet134_GasStation();
        int[] gas = {1,2,3,4,5};
        int[] cost = {3,4,5,1,2};

        System.out.println(ins.canCompleteCircuit(gas, cost));
    }
}
