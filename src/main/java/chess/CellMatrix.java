package chess;

//class contains the matrices needed to store the cells's content and their belongins
//two matrixs are used, playerMatrix and pieceMatrix
//whose content has different meaning
//0 -> empty, 1-> player one, 2 -> player two
//0 -> pawn, 1 -> rock, 2 -> knight, 3 -> bishop, 4 -> queen, 5 -> king, 6 -> empty
public class CellMatrix {

    private final int[][] playerMatrix = new int[8][8]; //Stores which player is in a cell. 0 empty, 1 player one, 2 player two
    private final int[][] pieceMatrix = new int[8][8]; //Stores which piece is in a cell. 0 pawn, ## 1 rock, 2 knight, 3 bishop, 4 queen, 5 king, 6 empty

    // resets initial positions of each pieces

    public void resetMatrix() {

        for (int row = 0; row < 8; row++) {

            for (int column = 0; column < 8; column++) {

                if (row <= 1) //first two rows
                {

                    playerMatrix[row][column] = 2;//player two there

                    if (row == 1) {
                        pieceMatrix[row][column] = 0;//place second player's pawn in 1th row
                    }

                } else if (row >= 2 && row <= 5)//empty spaces
                {

                    playerMatrix[row][column] = 0;
                    pieceMatrix[row][column] = 6;

                } else {

                    playerMatrix[row][column] = 1;//first player

                    if (row == 6) {
                        pieceMatrix[row][column] = 0;//place first player's pawn in 6th row
                    }

                }

                if (row == 0 || row == 7)//place rock-knight-bishop-queen-king-bishop-knight-rock  for both player
                {

                    if (column < 5) {
                        pieceMatrix[row][column] = (column + 1);
                    } else {
                        pieceMatrix[row][column] = (8 - column);
                    }

                }

            }

        }

    }

    public int getPlayerCell(int row, int column) {
        return playerMatrix[row][column];
    }

    public int getPieceCell(int row, int column) {
        return pieceMatrix[row][column];
    }

    public void setPlayerCell(int row, int column, int player) {
        playerMatrix[row][column] = player;
    }

    public void setPieceCell(int row, int column, int piece) {
        pieceMatrix[row][column] = piece;
    }

    public int[][] getPlayerMatrix() {
        return playerMatrix;
    }

    public int[][] getPieceMatrix() {
        return pieceMatrix;
    }

    private int r = 0;
    private int c = 0;

    public int getKingRow(int player) {

        for (r = 0; r < 8; r++) {
            for (c = 0; c < 8; c++) {
                System.err.println(" Checked Row   " + r + " Column " + c);
                if (playerMatrix[r][c] == player && pieceMatrix[r][c] == 5)

                    return r;
            }

        }
        return r;

    }

    public int getKingCol(int player) {

        for (r = 0; r < 8; r++) {
            for (c = 0; c < 8; c++) {
                System.err.println(" Checked Row   " + r + " Column " + c);
                if (playerMatrix[r][c] == player && pieceMatrix[r][c] == 5)
                    return c;

            }

        }
        return c;
    }

    public boolean isKingSafe(int player, int kingRow, int kingCol) {

        int prePlayer;

        if (player == 1) {
            prePlayer = 2;
            System.err.println("current player is player 1");
            System.err.println("pre player is player 2");
        } else {
            prePlayer = 1;
            System.err.println("current player is player 2");
            System.err.println("pre player is player 1");
        }

        //check for pawn
        if (player == 1) {
            r = kingRow - 1;
            c = kingCol - 1;
            //System.err.println(" Checked Row   "+r+" Column "+c);
            if (r >= 0 && c >= 0 && r <= 7 && c <= 7 && playerMatrix[r][c] == prePlayer && pieceMatrix[r][c] == 0) {
                return false;
            }
            r = kingRow - 1;
            c = kingCol + 1;
            //System.err.println(" Checked Row   "+r+" Column "+c);
            if (r >= 0 && c >= 0 && r <= 7 && c <= 7 && playerMatrix[r][c] == prePlayer && pieceMatrix[r][c] == 0) {
                return false;
            }
        } else {
            r = kingRow + 1;
            c = kingCol - 1;
            //System.err.println(" Checked Row   "+r+" Column "+c);
            if (r >= 0 && c >= 0 && r <= 7 && c <= 7 && playerMatrix[r][c] == prePlayer && pieceMatrix[r][c] == 0) {
                return false;
            }
            r = kingRow + 1;
            c = kingCol + 1;
            //System.err.println(" Checked Row   "+r+" Column "+c);
            if (r >= 0 && c >= 0 && r <= 7 && c <= 7 && playerMatrix[r][c] == prePlayer && pieceMatrix[r][c] == 0) {
                return false;
            }

        }
        System.err.println("pawn is checked");

        //check for knight
        r = kingRow - 2;
        c = kingCol - 1;
        //System.err.println(" Checked Row   "+r+" Column "+c);
        if (r >= 0 && c >= 0 && r <= 7 && c <= 7 && playerMatrix[r][c] == prePlayer && pieceMatrix[r][c] == 2) {
            return false;
        }

        r = kingRow - 2;
        c = kingCol + 1;
        //System.err.println(" Checked Row   "+r+" Column "+c);
        if (r >= 0 && c >= 0 && r <= 7 && c <= 7 && playerMatrix[r][c] == prePlayer && pieceMatrix[r][c] == 2) {
            return false;
        }

        r = kingRow + 2;
        c = kingCol - 1;
        //System.err.println(" Checked Row   "+r+" Column "+c);
        if (r >= 0 && c >= 0 && r <= 7 && c <= 7 && playerMatrix[r][c] == prePlayer && pieceMatrix[r][c] == 2) {
            return false;
        }

        r = kingRow + 2;
        c = kingCol + 1;
        //System.err.println(" Checked Row   "+r+" Column "+c);
        if (r >= 0 && c >= 0 && r <= 7 && c <= 7 && playerMatrix[r][c] == prePlayer && pieceMatrix[r][c] == 2) {
            return false;
        }

        r = kingRow - 1;
        c = kingCol - 2;
        //System.err.println(" Checked Row   "+r+" Column "+c);
        if (r >= 0 && c >= 0 && r <= 7 && c <= 7 && playerMatrix[r][c] == prePlayer && pieceMatrix[r][c] == 2) {
            return false;
        }

        r = kingRow - 1;
        c = kingCol + 2;
        //System.err.println(" Checked Row   "+r+" Column "+c);
        if (r >= 0 && c >= 0 && r <= 7 && c <= 7 && playerMatrix[r][c] == prePlayer && pieceMatrix[r][c] == 2) {
            return false;
        }

        r = kingRow + 1;
        c = kingCol - 2;
        //System.err.println(" Checked Row   "+r+" Column "+c);
        if (r >= 0 && c >= 0 && r <= 7 && c <= 7 && playerMatrix[r][c] == prePlayer && pieceMatrix[r][c] == 2) {
            return false;
        }

        r = kingRow + 1;
        c = kingCol + 2;
        //System.err.println(" Checked Row   "+r+" Column "+c);
        if (r >= 0 && c >= 0 && r <= 7 && c <= 7 && playerMatrix[r][c] == prePlayer && pieceMatrix[r][c] == 2) {
            return false;
        }

        System.err.println("knight is checked");

        //check for rook and queen in north
        c = kingCol;
        for (int row = kingRow; row > 0; row--) {
            r = row - 1;
            if (r >= 0 && c >= 0 && r <= 7 && c <= 7 && playerMatrix[r][c] == prePlayer && (pieceMatrix[r][c] == 1 || pieceMatrix[r][c] == 4)) {
                return false;
            }
            if (pieceMatrix[r][c] != 6) {
                break;
            }
            //System.err.println(" Checked Row   "+r+" Column "+c);
        }

        //check for rook and queen in south
        c = kingCol;
        for (int row = kingRow; row < 7; row++) {
            r = row + 1;
            if (r >= 0 && c >= 0 && r <= 7 && c <= 7 && playerMatrix[r][c] == prePlayer && (pieceMatrix[r][c] == 1 || pieceMatrix[r][c] == 4)) {
                return false;
            }
            if (pieceMatrix[r][c] != 6) {
                break;
            }
            //System.err.println(" Checked Row   "+r+" Column "+c);
        }

        //check for rook and queen in west
        r = kingRow;
        for (int col = kingCol; col > 0; col--) {
            c = col - 1;
            if (r >= 0 && c >= 0 && r <= 7 && c <= 7 && playerMatrix[r][c] == prePlayer && (pieceMatrix[r][c] == 1 || pieceMatrix[r][c] == 4)) {
                return false;
            }
            if (pieceMatrix[r][c] != 6) {
                break;
            }
            //System.err.println(" Checked Row   "+r+" Column "+c);
        }

        //check for rook and queen in east
        r = kingRow;
        for (int col = kingCol; col < 7; col++) {
            c = col + 1;
            if (r >= 0 && c >= 0 && r <= 7 && c <= 7 && playerMatrix[r][c] == prePlayer && (pieceMatrix[r][c] == 1 || pieceMatrix[r][c] == 4)) {
                return false;
            }
            if (pieceMatrix[r][c] != 6) {
                break;
            }
            //System.err.println(" Checked Row   "+r+" Column "+c);
        }
        System.err.println("straight is checked");

        //check diagonally for bishop and queen
        //check for bishop and queen in north-west
        r = kingRow - 1;
        c = kingCol - 1;
        while (r >= 0 && c >= 0) {
            if (r >= 0 && c >= 0 && r <= 7 && c <= 7 && playerMatrix[r][c] == prePlayer && (pieceMatrix[r][c] == 3 || pieceMatrix[r][c] == 4)) {
                return false;
            }
            if (pieceMatrix[r][c] != 6) {
                break;
            }
            //System.err.println(" Checked Row   "+r+" Column "+c);
            r--;
            c--;
        }

        //check for bishop and queen in north-east
        r = kingRow - 1;
        c = kingCol + 1;
        while (r >= 0 && c <= 7) {
            if (r >= 0 && c >= 0 && r <= 7 && c <= 7 && playerMatrix[r][c] == prePlayer && (pieceMatrix[r][c] == 3 || pieceMatrix[r][c] == 4)) {
                return false;
            }
            if (pieceMatrix[r][c] != 6) {
                break;
            }
            //System.err.println(" Checked Row   "+r+" Column "+c);
            r--;
            c++;
        }

        //check for bishop and queen in south-west
        r = kingRow + 1;
        c = kingCol - 1;
        while (r <= 7 && c >= 0) {
            if (r >= 0 && c >= 0 && r <= 7 && c <= 7 && playerMatrix[r][c] == prePlayer && (pieceMatrix[r][c] == 3 || pieceMatrix[r][c] == 4)) {
                return false;
            }
            if (pieceMatrix[r][c] != 6) {
                break;
            }
            //System.err.println(" Checked Row   "+r+" Column "+c);
            r++;
            c--;
        }

        //check for bishop and queen in south-east
        r = kingRow + 1;
        c = kingCol + 1;
        while (r <= 7 && c <= 7) {
            if (r < 8 && c < 8 && r >= 0 && c >= 0 && playerMatrix[r][c] == prePlayer && (pieceMatrix[r][c] == 3 || pieceMatrix[r][c] == 4)) {
                return false;
            }
            if (pieceMatrix[r][c] != 6) {
                break;
            }
            //System.err.println(" Checked Row   "+r+" Column "+c);
            r++;
            c++;
        }
        System.err.println("diagonal is checked");

        return true;
    }

    public void printPlayerCell() {
        for (int row = 0; row < 8; row++) {

            for (int column = 0; column < 8; column++) {

                System.err.print(playerMatrix[row][column] + "\t");

            }
            System.err.print("\n");

        }
    }

    public void printPieceCell() {
        for (int row = 0; row < 8; row++) {

            for (int column = 0; column < 8; column++) {

                System.err.print(pieceMatrix[row][column] + "\t");

            }
            System.err.print("\n");

        }
    }

    public boolean checkWinner(int currentPlayer) {

        int checkPlayer;

        if (currentPlayer == 1) {
            checkPlayer = 2;
        } else {
            checkPlayer = 1;
        }

        for (int row = 0; row < 8; row++) {

            for (int column = 0; column < 8; column++) {

                if (playerMatrix[row][column] == checkPlayer && pieceMatrix[row][column] == 5) //If the enemy's king still remains
                {
                    return false;
                }

            }

        }

        return true;

    }
}
