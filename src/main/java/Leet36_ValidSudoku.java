import java.util.Arrays;

public class Leet36_ValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        boolean[] rCheck = new boolean[9];
        boolean[] cCheck = new boolean[9];
        boolean[] bCheck = new boolean[9];

        for(int i = 0; i < 9; ++i) {
            for(int j = 0; j < 9; ++j) {
                char cTmp = board[i][j];
                if(cTmp != '.' && rCheck[cTmp - '1'])
                    return false;
                else if(cTmp != '.')
                    rCheck[cTmp - '1'] = true;

                cTmp = board[j][i];
                if(cTmp != '.' && cCheck[cTmp - '1'])
                    return false;
                else if(cTmp != '.')
                    cCheck[cTmp - '1'] = true;

                cTmp = board[3 * (i / 3) + (j / 3)][3 * (i % 3) + (j % 3)];
                if(cTmp != '.' && bCheck[cTmp - '1'])
                    return false;
                else if(cTmp != '.')
                    bCheck[cTmp - '1'] = true;
            }
            Arrays.fill(rCheck, false);
            Arrays.fill(cCheck, false);
            Arrays.fill(bCheck, false);
        }

        return true;
    }
}
