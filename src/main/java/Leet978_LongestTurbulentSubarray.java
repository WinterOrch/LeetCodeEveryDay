public class Leet978_LongestTurbulentSubarray {
    public int maxTurbulenceSize(int[] arr) {
        if (arr.length == 1) {
            return 1;
        } else if (arr.length == 2) {
            return (arr[0] == arr[1]) ? 1 : 2;
        }

        int left = 0, right = 1;
        int res = 0;

        boolean goingUp = (arr[1] > arr[0]);

        for (; right < arr.length; ++right) {
            if (left == right - 1) {
                goingUp = arr[right] > arr[right - 1];
            }

            if (goingUp == arr[right] > arr[right - 1] || arr[right] == arr[right - 1]) {
                res = Math.max(res, right - left);

                left = (arr[right] == arr[right - 1]) ? right : right - 1;
            } else {
                goingUp = !goingUp;
            }
        }

        res = Math.max(res, right - left);

        return res;
    }

    public static void main(String[] args) {
        Leet978_LongestTurbulentSubarray ins = new Leet978_LongestTurbulentSubarray();
        System.out.println(ins.maxTurbulenceSize(new int[]{100, 100, 100}));
    }
}
