public class Leet838_PushDominoes {
    /**
     * 其实这里由于长度是确定的，直接用char[]要比用StringBuilder快一些
     * created in 21:15 2021/4/5
     */
    public String pushDominoes(String dominoes) {
        int len = dominoes.length();

        char[] cArray = new char[len + 2];
        cArray[0] = 'L';
        cArray[len + 1] = 'R';
        System.arraycopy(dominoes.toCharArray(), 0, cArray, 1, len);
        StringBuilder res = new StringBuilder();

        for(int i = 0, j = 1; j < cArray.length; ++j) {
            if('.' == cArray[j])
                continue;

            int numMid = j - i - 1;

            if(0 != i)
                res.append(cArray[i]);
            if(cArray[i] == cArray[j])
                for(int k = 0; k < numMid; ++k)
                    res.append(cArray[i]);
            else if('R' == cArray[i] && 'L' == cArray[j]) {
                for(int k = 0; k < numMid / 2; ++k)
                    res.append('R');
                for(int k = 0; k < numMid % 2; ++k)
                    res.append('.');
                for(int k = 0; k < numMid / 2; ++k)
                    res.append('L');
            }else {
                for(int k = 0; k < numMid; ++k)
                    res.append('.');
            }

            i = j;
        }

        return res.toString();
    }
}
