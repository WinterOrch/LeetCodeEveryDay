public class Leet461_HammingDistance {
    public int hammingDistance(int x, int y) {
        int tmp = x ^ y, res = 0;

        while (tmp > 0) {
            if ((tmp & 0x01) > 0) {
                ++res;
            }
            tmp >>= 1;
        }

        return res;
    }

    public int hammingDistance_inOneLine(int x, int y) {
        return Integer.bitCount(x ^ y);
    }

    public static void main(String[] args) {
        Leet461_HammingDistance ins = new Leet461_HammingDistance();

        System.out.println(ins.hammingDistance_inOneLine(2, 8));
    }
}
