public class Leet12_IntegerToRoman {
    /**
     * 偷懒方法
     * created in 17:56 2021/5/14
     */
    public String intToRoman(int num) {
        String[] belowTen = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        String[] belowHundred = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] belowThousand = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};

        StringBuilder res = new StringBuilder();

        int one = num % 10;
        num /= 10;
        int ten = num % 10;
        num /= 10;
        int hun = num % 10;
        num /= 10;
        int tho = num % 10;

        for (int i = 0; i < tho; ++i) {
            res.append('M');
        }

        res.append(belowThousand[hun]);
        res.append(belowHundred[ten]);
        res.append(belowTen[one]);

        return res.toString();
    }

    /**
     * 老实办法
     * created in 17:56 2021/5/14
     */
    public String intToRoman_2(int num) {
        StringBuilder romanForm = new StringBuilder();

        int[] val = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] str = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        for (int i = 0; i < val.length; ++i) {
            while (num >= val[i]) {
                num -= val[i];
                romanForm.append(str[i]);
            }
        }

        return romanForm.toString();
    }

    public static void main(String[] args) {
        Leet12_IntegerToRoman ins = new Leet12_IntegerToRoman();

        System.out.println(ins.intToRoman(1994));
    }
}
