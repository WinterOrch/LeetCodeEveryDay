public class Leet419_BattleshipsInABoard {
    public int countBattleships(char[][] board) {
        if(board.length < 1)
            return 0;

        int res = 0;

        for(int i = 0; i < board.length; ++i) {
            for(int j = 0; j < board[0].length; ++j) {
                // 只有左边或上面一格为船体才有可能是一条已被计数过船的延伸部分
                if (board[i][j] == '.'
                        || (i > 0 && board[i - 1][j] == 'X')
                        || (j > 0 && board[i][j - 1] == 'X'))
                    continue;

                ++res;
            }
        }

        return res;
    }
}
