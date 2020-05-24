package chess;

public class Pawn extends BasePiece {

    public boolean legalMove(int startRow, int startColumn, int desRow, int desColumn, int[][] playerMatrix, int currentPlayer) {

        boolean legalMove = true;
        int[] playerPawnStart = {6, 1};

        if ((currentPlayer == 1 && desRow >= startRow) || (currentPlayer == 2 && desRow <= startRow)) //If moving in wrong direction
        {

            strErrorMsg = "Can not move in that direction";
            legalMove = false;

        } else if (desColumn != startColumn) //If moving sideways
        {

            if ((desColumn > startColumn && desColumn == (startColumn + 1)) || (desColumn < startColumn && desColumn == (startColumn - 1))) //If only moving one place side ways
            {

                if ((desRow == (startRow + 1) && currentPlayer == 2) || (desRow == (startRow - 1) && currentPlayer == 1)) {

                    if (playerMatrix[desRow][desColumn] == 0) //If cell is empty
                    {

                        strErrorMsg = "Can only move diagonal when taking an enemy piece";
                        legalMove = false;

                    }

                } else {

                    strErrorMsg = "Can not move that far";
                    legalMove = false;

                }

            } else {

                strErrorMsg = "Can not move that far";
                legalMove = false;

            }

        } else if ((currentPlayer == 1 && desRow < (startRow - 1)) || (currentPlayer == 2 && desRow > (startRow + 1))) //If moved two or more places
        {

            if ((currentPlayer == 1 && desRow == (startRow - 2)) || (currentPlayer == 2 && desRow == (startRow + 2))) //If moved two places
            {

                if (playerPawnStart[currentPlayer - 1] != startRow) //If not at pawn starting place
                {

                    strErrorMsg = "Can not move that far";
                    legalMove = false;

                }

            } else {

                strErrorMsg = "Can not move that far";
                legalMove = false;

            }

        } else if (desColumn == startColumn) {
            if (playerMatrix[desRow][desColumn] != 0) {
                strErrorMsg = "Can not move in that Cell";
                legalMove = false;
            }

        }

        if (legalMove) {

            finalDesRow = desRow;
            finalDesColumn = desColumn;

        }

        return legalMove;

    }

}	