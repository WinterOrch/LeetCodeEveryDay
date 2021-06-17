public class Leet1011_CapacityToShipPackagesWithinDDays {
    public int shipWithinDays(int[] weights, int D) {
        int right = 0, max = 0;

        for(int weight : weights) {
            right += weight;
            if(weight > max)
                max = weight;
        }

        int left = right / D;
        if(D * left < right)
            left++;

        left = Math.max(left, max);

        while(left < right) {
            int mid = left + (right - left) / 2;

            int cap = 0;
            int reqDays = 1;

            for(int weight : weights) {
                if (cap + weight > mid) {
                    ++reqDays;
                    cap = 0;
                }
                cap += weight;
            }

            if(reqDays > D) {
                left = mid + 1;
            }else {
                right = mid;
            }
        }

        return left;
    }

    public static void main(String[] args) {
        Leet1011_CapacityToShipPackagesWithinDDays ins = new Leet1011_CapacityToShipPackagesWithinDDays();

        int[] weights = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int D = 5;

        System.out.println(ins.shipWithinDays(weights, D));
    }
}
